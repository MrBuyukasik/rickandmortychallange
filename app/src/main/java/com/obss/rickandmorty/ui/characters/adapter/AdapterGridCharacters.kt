package com.obss.rickandmorty.ui.characters.adapter

import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.view.ViewGroup
import com.obss.rickandmorty.R
import com.obss.rickandmorty.binding.DataBoundAdapter
import com.obss.rickandmorty.binding.DataBoundViewHolder
import com.obss.rickandmorty.databinding.CharacterGridItemBinding
import com.obss.rickandmorty.interfaces.ICharacters
import com.obss.rickandmorty.model.CharacterObjectModel
import com.obss.rickandmorty.utils.SharedPreferencesUtils

class AdapterGridCharacters(
    private val characters: ArrayList<CharacterObjectModel>,
    private val iCharacters: ICharacters
) :
    DataBoundAdapter<CharacterGridItemBinding>(R.layout.character_grid_item) {
    private lateinit var characterIds: ArrayList<Int>


    override fun bindItem(
        holder: DataBoundViewHolder<CharacterGridItemBinding>,
        position: Int,
        payloads: List<Any>
    ) {
        holder.binding.data = characters[position]
        holder.binding.callback = iCharacters
        val shape = GradientDrawable()
        shape.cornerRadius = 10.0F
        when (characters[position].status) {
            "Alive" -> {
                shape.setColor(Color.GREEN)
                holder.binding.characterStatusColor.background = shape
            }
            "Dead" -> {
                shape.setColor(Color.RED)
                holder.binding.characterStatusColor.background = shape
            }
            "unknown" -> {
                shape.setColor(Color.GRAY)
                holder.binding.characterStatusColor.background = shape
            }
            else -> {
                shape.setColor(Color.GRAY)
                holder.binding.characterStatusColor.background = shape
            }
        }

        characterIds = SharedPreferencesUtils.getFavoriteCharacterIdList(holder.itemView.context)
        if (characterIds.contains(characters[position].id)) {
            holder.binding.characterFavoriteIcon.setBackgroundResource(R.drawable.ic_favorited)
        } else {
            holder.binding.characterFavoriteIcon.setBackgroundResource(R.drawable.ic_favorite_default)
        }
    }


    override fun getItemCount(): Int {
        return characters.size
    }

}