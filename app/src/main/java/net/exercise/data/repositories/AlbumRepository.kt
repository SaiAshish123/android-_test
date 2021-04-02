package net.exercise.data.repositories

import net.exercise.data.network.AlbumApi

class AlbumRepository(
    private val api: AlbumApi
) : SafeApiRequest() {

    suspend fun getAlbum() = apiRequest { api.getAlbum() }

}