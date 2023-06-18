package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Photo
import com.example.androidplaceholder.data.remote.RetrofitService
import javax.inject.Inject

class PhotosRepository
    @Inject constructor(private val retrofitService: RetrofitService): IPhotosRepository {
    override suspend fun getSmallPhotos(): List<Photo.PhotoSmaller> {
        val response = retrofitService.getPhotosList()
        if (response.isSuccessful) {

            val photos = mutableListOf<Photo.PhotoSmaller>()

            for(photo in response.body()!!){
                photos.add(Photo.getSmallPhoto(photo))
            }

            return photos
        }
        return listOf()
    }

    override suspend fun getPhotosByAlbumId(albumId: Int): List<Photo.PhotoBigger> {
        val response = retrofitService.getPhotoListByAlbumId(albumId)
        if (response.isSuccessful) {

            val photos = mutableListOf<Photo.PhotoBigger>()

            for(photo in response.body()!!){
                photos.add(Photo.getBigPhoto(photo))
            }

            return photos
        }
        return listOf()
    }

}