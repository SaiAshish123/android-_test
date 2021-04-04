package net.exercise.ui.movies

import android.content.SharedPreferences
import android.preference.PreferenceManager
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import net.exercise.R
import net.exercise.databinding.RecyclerviewAlbumBinding
import net.simplifiedcoding.data.models.AlbumItem

/**
 * @author Ashish
 * This class is used for Creating Custom Listview using Data binding Concept
 */
class AlbumsAdapter (
    private val albumslst: List<AlbumItem>,
    private val listener: RecyclerViewClickListener
) : RecyclerView.Adapter<AlbumsAdapter.AlbumViewHolder>(){

    /**
     * Get the List of Albums items
     */
    override fun getItemCount() = albumslst.size

    /**
     * Creating the View Holder Method for Settup Layout and ids using Databinding
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        AlbumViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.recyclerview_album,
                parent,
                false
            )
        )

    /**
     * Sorting function by title Object
     * Position the items inside listview using custom record
     */
    override fun onBindViewHolder(holder: AlbumViewHolder, position: Int) {
        var sortedList = albumslst.sortedWith(compareBy({ it.title }))


        holder.recyclerviewAlbumBinding.album = sortedList[position]

    }


    /**
     * Creating the View Holder object using Binding
     *
     */
    inner class AlbumViewHolder(
        val recyclerviewAlbumBinding: RecyclerviewAlbumBinding
    ) : RecyclerView.ViewHolder(recyclerviewAlbumBinding.root)

}