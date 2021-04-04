package net.exercise.data.repositories

import net.exercise.data.network.AlbumApi

/**
 * @author Ashish
 */
class AlbumRepository( private val api: AlbumApi) : SafeApiRequest() {

    /**
     * Calling the API Request using get Album API
     */
    suspend fun getAlbum() = apiRequest { api.getAlbum() }

}