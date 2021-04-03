package net.exercise.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.exercise.util.Coroutines
import net.exercise.data.repositories.AlbumRepository
import net.simplifiedcoding.data.models.AlbumItem

class AlbumViewModel(
    private val repository: AlbumRepository
) : ViewModel() {

    private lateinit var job: Job

    private val albums_mutable = MutableLiveData<List<AlbumItem>>()
    val live_album: LiveData<List<AlbumItem>>
        get() = albums_mutable

    fun getAlbums() {
        job = Coroutines.ioThenMain(
            { repository.getAlbum() },
            { albums_mutable.value = it }
        )
    }

    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
