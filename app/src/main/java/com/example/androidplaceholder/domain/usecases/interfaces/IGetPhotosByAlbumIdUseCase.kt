package com.example.androidplaceholder.domain.usecases.interfaces

import com.example.androidplaceholder.data.models.Photo

interface IGetPhotosByAlbumIdUseCase {
    suspend operator fun invoke(albumId: Int): List<Photo.PhotoBigger>
}