package net.exercise.data.network

import net.simplifiedcoding.data.models.AlbumItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

/**
 * @author Ashish
 * This class is used to call the API using Base URL and Asynck Call
 */
interface AlbumApi {

    /**
     * Call the End Point method to get the List of Album Data
     */
    @GET("albums")
    suspend fun getAlbum() : Response<List<AlbumItem>>


    /**
     * Singleton Object class for instance create
     */
    companion object{
        operator fun invoke() : AlbumApi {
            return Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl("https://jsonplaceholder.typicode.com/")
                .build()
                .create(AlbumApi::class.java)
        }
    }
}
