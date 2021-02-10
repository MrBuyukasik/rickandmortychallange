package com.obss.rickandmorty.ui.characters

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.lifecycle.Observer
import androidx.navigation.fragment.NavHostFragment
import androidx.recyclerview.widget.GridLayoutManager
import com.obss.rickandmorty.R
import com.obss.rickandmorty.base.BaseFragment
import com.obss.rickandmorty.databinding.CharactersFragmentBinding
import com.obss.rickandmorty.interfaces.ICharacters
import com.obss.rickandmorty.model.CharacterInfoModel
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.ui.characters.adapter.AdapterListCharacters
import com.obss.rickandmorty.ui.characters.adapter.AdapterGridCharacters
import com.obss.rickandmorty.ui.characters.adapter.EndlessRecyclerOnScrollListener
import java.util.*
import kotlin.collections.ArrayList

class CharactersFragment : BaseFragment<CharactersViewModel, CharactersFragmentBinding>(),
    ICharacters {

    private var layoutManager: GridLayoutManager? = null

    override val getLayoutId: Int = R.layout.characters_fragment
    override val viewModelClass = CharactersViewModel::class.java
    private val characterList: ArrayList<CharacterObjectModel> = arrayListOf()
    private var characterListInfo: CharacterInfoModel? = null
    private var endlessScrollListener: EndlessRecyclerOnScrollListener? = null
    private var characterListAdapter = AdapterListCharacters(characterList, this)
    private var characterGridAdapter = AdapterGridCharacters(characterList, this)

    companion object {
        var isGridViewType: Boolean = true
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        init()
    }

    private fun init() {
        initData()
        initRecycler()
        observe()
    }

    private fun initData() {
        if (characterList.isNullOrEmpty()) {
            viewModel.getAllCharacters()
        }
        binding.listViewTypeText.setOnClickListener {
            isGridViewType =
                binding.listViewTypeText.text == getString(R.string.action_bar_grid_title)
            initRecycler()
            observe()
        }

    }

    private fun initRecycler() {
        initBindings()
        initEndlessRecycler()

    }

    private fun initBindings() {
        if (isGridViewType) {
            layoutManager = GridLayoutManager(context, 2)
            binding.recyclerCharacters.layoutManager = layoutManager
            binding.recyclerCharacters.adapter = characterGridAdapter
            binding.searchEditText.text?.clear()
            binding.listViewTypeText.text = getString(R.string.action_bar_list_title)
        } else {
            layoutManager = GridLayoutManager(context, 1)
            binding.recyclerCharacters.layoutManager = layoutManager
            binding.recyclerCharacters.adapter = characterListAdapter
            binding.searchEditText.text?.clear()
            binding.listViewTypeText.text = getString(R.string.action_bar_grid_title)
        }
    }

    private fun initEndlessRecycler() {
        endlessScrollListener = object :
            EndlessRecyclerOnScrollListener(binding.recyclerCharacters.layoutManager as GridLayoutManager) {
            override fun onLoadMore() {
                if (characterList.size > 19 && binding.searchEditText.text.isNullOrEmpty()) {
                    characterListInfo?.next?.let {
                        loadMoreCharacters(it)
                    }
                }
            }
        }
        binding.recyclerCharacters.addOnScrollListener(endlessScrollListener as EndlessRecyclerOnScrollListener)
    }

    private fun observe() {
        viewModel.characterList.observe(viewLifecycleOwner, Observer {
            characterList.clear()
            characterList.addAll(it)
            initRecyclerAdapter()
            binding.searchEditText.addTextChangedListener(object : TextWatcher {
                override fun afterTextChanged(s: Editable?) {

                }

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(
                    charSequence: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                    charSequence?.let { char ->
                        if (char.isEmpty()) {
                            viewModel.characterList.observe(
                                viewLifecycleOwner,
                                Observer { allCharacters ->
                                    characterList.clear()
                                    characterList.addAll(allCharacters)
                                    initRecyclerAdapter()
                                })
                            initEndlessRecycler()
                            characterGridAdapter.notifyDataSetChanged()
                        } else {
                            viewModel.characterList.observe(
                                viewLifecycleOwner,
                                Observer { allCharacters ->
                                    characterList.clear()
                                    characterList.addAll(allCharacters)
                                })
                            val filteredList = characterList.filter { characterObjectModel ->
                                characterObjectModel.name!!.toLowerCase(Locale.getDefault())
                                    .contains(
                                        char.toString().toLowerCase(Locale.getDefault())
                                    ) || characterObjectModel.status!!.toLowerCase(Locale.getDefault())
                                    .contains(char.toString().toLowerCase(Locale.getDefault()))
                            }

                            characterList.clear()
                            characterList.addAll(filteredList)
                            initRecyclerAdapter()
                        }
                    }
                }
            })
        })
        viewModel.characterListInfo.observe(viewLifecycleOwner, Observer {
            characterListInfo = it
        })
    }

    private fun initRecyclerAdapter() {
        if (isGridViewType) {
            characterGridAdapter.notifyDataSetChanged()
        } else {
            characterListAdapter.notifyDataSetChanged()
        }
    }


    override fun onCharactersClicked(characters: CharacterObjectModel) {
        val bundle = Bundle()
        bundle.putSerializable("CHARACTERS_MODEL", characters)
        NavHostFragment.findNavController(this)
            .navigate(R.id.action_charactersFragment_to_characterDetailFragment, bundle)
    }


    private fun loadMoreCharacters(nextCharacterListUrl: String) {
        viewModel.getMoreCharacters(nextCharacterListUrl)

    }

    override fun onResume() {
        super.onResume()
        binding.searchEditText.text?.clear()
    }
}