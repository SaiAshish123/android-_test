package net.exercise.ui.movies

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.Job
import net.exercise.util.Coroutines
import net.exercise.data.repositories.AlbumRepository
import net.simplifiedcoding.data.models.AlbumItem

/**
 * @author Ashish
 * This View Model Class is to get the data using Main thread
 */
class AlbumViewModel( private val repository: AlbumRepository) : ViewModel() {

    /**
     * Declares A `Job` instance in the Couroutine
     */
    private lateinit var job: Job

    /**
     * Declaring the private instance  of Mutable object data
     */
    private val albums_mutable = MutableLiveData<List<AlbumItem>>()

    /**
     * To access from our activity we are using Live data to get the Updated value using Observer pattern
     */
    val live_album: LiveData<List<AlbumItem>>
        get() = albums_mutable

    /**
     * Get Albums using Couroutine Utils using Main thread
     */
    fun getAlbums() {
        job = Coroutines.ioThenMain(
            { repository.getAlbum() },
            { albums_mutable.value = it }
        )
    }

    /**
     * Once the Job is Completed it is used to cleared
     */
    override fun onCleared() {
        super.onCleared()
        if(::job.isInitialized) job.cancel()
    }
}
