package net.exercise.ui.movies

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import net.exercise.data.repositories.AlbumRepository

@Suppress("UNCHECKED_CAST")
class AlbumViewModelFactory(
    private val repository: AlbumRepository
) : ViewModelProvider.NewInstanceFactory(){

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return AlbumViewModel(repository) as T
    }

}