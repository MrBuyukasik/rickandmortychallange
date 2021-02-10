package com.obss.rickandmorty.ui.characters.adapter

import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView

abstract class EndlessRecyclerOnScrollListener(private val gridLayoutManager: GridLayoutManager) :
    RecyclerView.OnScrollListener() {

    private var previousTotal = 0

    private var loading = true
    abstract fun onLoadMore()
    override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
        super.onScrolled(recyclerView, dx, dy)
        val totalItemCount = gridLayoutManager.itemCount
        val visibleItemCount = gridLayoutManager.childCount;
        val firstVisibleItem = gridLayoutManager.findFirstVisibleItemPosition()
        if (loading) {
            if (totalItemCount > previousTotal) {
                loading = false
                previousTotal = totalItemCount
            }
        }
        if (!loading && (totalItemCount - visibleItemCount) <= (firstVisibleItem)) {
            onLoadMore()
            loading = true
        }
    }

}