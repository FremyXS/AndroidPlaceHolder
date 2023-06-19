package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.data.remote.RetrofitService
import com.example.androidplaceholder.domain.repositories.interfaces.IAlbumsRepository
import javax.inject.Inject

class AlbumsRepository
    @Inject constructor(private val retrofitService: RetrofitService): IAlbumsRepository {
    override suspend fun getAlbums(): List<AlbumDefault.Album> {
        val response = retrofitService.getAlbumList()
        if (response.isSuccessful) {
            val posts = mutableListOf<AlbumDefault.Album>()

            for (album in response.body()!!){
                posts.add(AlbumDefault.getAlbum(album))
            }

            return posts
        }
        return listOf()
    }
}