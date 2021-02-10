package com.obss.rickandmorty.binding

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

object BindingAdapters {
    @JvmStatic
    @BindingAdapter(value = ["loadImage"], requireAll = false)
    fun loadImage(view: ImageView, url: String?) {
        url?.let {
            Picasso.get().load(it).into(view)
        }
    }

}
