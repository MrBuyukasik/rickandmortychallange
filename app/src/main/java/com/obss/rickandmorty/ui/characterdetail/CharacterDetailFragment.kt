package com.obss.rickandmorty.ui.characterdetail

import android.graphics.Color
import android.os.Bundle
import androidx.lifecycle.Observer
import com.obss.rickandmorty.MainActivity
import com.obss.rickandmorty.R
import com.obss.rickandmorty.base.BaseFragment
import com.obss.rickandmorty.databinding.CharacterDetailFragmentBinding
import com.obss.rickandmorty.di.scope.Injectable
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.utils.SharedPreferencesUtils
import kotlin.collections.ArrayList

class CharacterDetailFragment :
    BaseFragment<CharacterDetailViewModel, CharacterDetailFragmentBinding>(), Injectable {

    private var characterDetails: CharacterObjectModel? = null
    private var characterDetailImage: String? = null
    override val getLayoutId: Int = R.layout.character_detail_fragment
    override val viewModelClass = CharacterDetailViewModel::class.java
    private var isCharacterFavorite = false
    lateinit var characterIdList: ArrayList<Int>

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getData()
        init()
        backPress()
    }

    private fun getData() {
        val arguments = arguments;
        arguments?.let {
            characterDetails = it.getSerializable("CHARACTERS_MODEL") as CharacterObjectModel
        }
        characterDetailImage = characterDetails?.image
        characterDetailImage?.let { viewModel.getCharacterDetailImage(it) }

    }

    private fun init() {
        characterDetails?.id?.let { characterId ->
            viewModel.getCharacterById(characterId)
        }
        observe()
    }

    private fun observe() {
        viewModel.character.observe(viewLifecycleOwner, Observer {
            binding.characterImage
            binding.characterName.text = it.name
            binding.characterGenderText.text = it.gender
            when (it.status) {
                "Alive" -> {
                    binding.characterStatus.setTextColor(Color.GREEN)
                    binding.characterStatusText.setTextColor(Color.GREEN)
                    binding.characterStatusText.text = it.status

                }
                "Dead" -> {
                    binding.characterStatus.setTextColor(Color.RED)
                    binding.characterStatusText.setTextColor(Color.RED)
                    binding.characterStatusText.text = it.status

                }
                "unknown" -> {
                    binding.characterStatus.setTextColor(Color.GRAY)
                    binding.characterStatusText.setTextColor(Color.GRAY)
                    binding.characterStatusText.text = it.status
                }
            }
            binding.characterSpeciesText.text = it.species
            binding.characterOriginText.text = it.origin?.name
            binding.characterLastSeenLocationText.text = it.location?.name
            binding.characterNumberOfEpisodeText.text = it.episode?.size.toString()

            characterIdList =
                context?.let { context -> SharedPreferencesUtils.getFavoriteCharacterIdList(context) }!!
            if (characterIdList.contains(characterDetails?.id)) {
                binding.characterFavoriteButton.setBackgroundResource(R.drawable.ic_favorited)
            } else {
                binding.characterFavoriteButton.setBackgroundResource(R.drawable.ic_favorite_default)
            }

            binding.characterFavoriteButton.setOnClickListener {
                characterDetails?.id?.let { id ->
                    if (characterIdList.contains(id)) {
                        characterIdList.remove(id)
                        binding.characterFavoriteButton.setBackgroundResource(R.drawable.ic_favorite_default)
                    } else {
                        characterIdList.add(id)
                        binding.characterFavoriteButton.setBackgroundResource(R.drawable.ic_favorited)
                    }
                    context?.let { context ->
                        SharedPreferencesUtils.addFavoriteCharacter(context, characterIdList)
                    }
                }
            }
        })

        viewModel.episode.observe(viewLifecycleOwner, Observer {
            binding.characterLastSeenEpisodeText.text = it.name
        })

    }

    private fun backPress() {
        binding.toolbarBack.setOnClickListener {
            (activity as MainActivity).onBackPressed()
        }
    }


}