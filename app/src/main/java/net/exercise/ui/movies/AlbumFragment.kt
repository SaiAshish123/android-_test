package net.exercise.ui.movies

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_fragment.*
import net.exercise.R
import net.exercise.data.network.AlbumApi
import net.exercise.data.repositories.AlbumRepository
import net.simplifiedcoding.data.models.AlbumItem


class AlbumFragment : Fragment(), RecyclerViewClickListener{

    private lateinit var factory: AlbumViewModelFactory
    private lateinit var viewModel: AlbumViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activity_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val api = AlbumApi()
        val repository = AlbumRepository(api)

        factory = AlbumViewModelFactory(repository)
        viewModel = ViewModelProviders.of(this, factory).get(AlbumViewModel::class.java)

        viewModel.getMovies()

        viewModel.movies.observe(viewLifecycleOwner, Observer { movies ->
            recycler_view_movies.also {
                it.layoutManager = LinearLayoutManager(requireContext())
                it.setHasFixedSize(true)
                it.adapter = AlbumsAdapter(movies, this)
            }
        })
    }

    override fun onRecyclerViewItemClick(view: View, movie: AlbumItem) {
        when(view.id){

        }
    }
}
