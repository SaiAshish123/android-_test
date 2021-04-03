package net.exercise.ui.movies

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import net.exercise.R
import net.exercise.databinding.RecyclerviewAlbumBinding
import net.simplifiedcoding.data.models.AlbumItem

class AlbumsAdapter (
    private val albumslst: List<AlbumItem>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    override fun getItemCount() = albumslst.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_album,
                parent,
                false
            )
        )

    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        var sortedList = albumslst.sortedWith(compareBy({ it.title }))
        holder.recyclerviewAlbumBinding.album = sortedList[position]

    }


    inner class AlbumViewHolder(
        val recyclerviewAlbumBinding: RecyclerviewAlbumBinding
    ) : RecyclerView.ViewHolder(recyclerviewAlbumBinding.root)

}