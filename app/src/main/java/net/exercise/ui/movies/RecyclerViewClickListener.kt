package net.exercise.ui.movies

import android.view.View
import net.simplifiedcoding.data.models.AlbumItem

/**
 * @author Ashish
 * this class is used for clickable fun
 */
interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, movie: AlbumItem)
}