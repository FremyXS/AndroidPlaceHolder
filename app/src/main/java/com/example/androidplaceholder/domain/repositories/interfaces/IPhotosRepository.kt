package com.example.androidplaceholder.domain.repositories.interfaces

import com.example.androidplaceholder.data.models.Photo

interface IPhotosRepository {
    suspend fun getSmallPhotos(): List<Photo.PhotoSmaller>
//    suspend fun getPhoto(): String
    suspend fun getPhotosByAlbumId(albumId: Int): List<Photo.PhotoBigger>
}