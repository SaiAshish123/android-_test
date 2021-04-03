package net.exercise

import androidx.lifecycle.ViewModelProviders

import net.exercise.data.repositories.AlbumRepository
import net.exercise.ui.movies.AlbumViewModel
import net.exercise.ui.movies.AlbumViewModelFactory
import net.simplifiedcoding.data.models.AlbumItem
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before
import org.mockito.Mock
import org.mockito.Mockito

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Mock
    private lateinit var factory: AlbumViewModelFactory
    @Mock
    private lateinit var repository: AlbumRepository
    @Mock
    private lateinit var viewModel: AlbumViewModel

    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }

    @Before
    fun setup() {

    }


    @Test
    fun  checkalbums() {
   
    }
}
