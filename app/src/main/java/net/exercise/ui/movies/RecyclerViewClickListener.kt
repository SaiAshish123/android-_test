package net.exercise.ui.movies

import android.view.View
import net.simplifiedcoding.data.models.AlbumItem

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, movie: AlbumItem)
}