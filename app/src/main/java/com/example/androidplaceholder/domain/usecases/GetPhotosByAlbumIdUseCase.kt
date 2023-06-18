package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.Photo
import com.example.androidplaceholder.domain.repositories.IPhotosRepository
import javax.inject.Inject

class GetPhotosByAlbumIdUseCase
    @Inject
    constructor(
        private val photosRepository: IPhotosRepository
        ): IGetPhotosByAlbumIdUseCase{

    override suspend fun invoke(albumId: Int): List<Photo.PhotoBigger> {
        return photosRepository.getPhotosByAlbumId(albumId)
    }
}