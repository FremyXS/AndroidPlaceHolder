package com.example.androidplaceholder.domain.repositories.interfaces

import com.example.androidplaceholder.data.models.AlbumDefault

interface IAlbumsRepository {
    suspend fun getAlbums(): List<AlbumDefault.Album>
}