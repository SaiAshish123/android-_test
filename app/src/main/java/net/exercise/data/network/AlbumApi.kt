package net.exercise.data.network

import net.simplifiedcoding.data.models.AlbumItem
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET

interface AlbumApi {

    @GET("albums")
    suspend fun getAlbum() : Response<List<AlbumItem>>


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
