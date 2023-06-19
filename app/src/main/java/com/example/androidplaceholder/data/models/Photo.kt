package com.example.androidplaceholder.data.models

import com.example.androidplaceholder.data.requests.PhotoRequest

sealed class Photo(
    open val albumId: Int?,
    open val id: Int?,
    open val title: String,
){
    data class PhotoSmaller(
        override val albumId: Int?,
        override val id: Int?,
        override val title: String,
        val thumbnailUrl: String
    ) : Photo(albumId, id, title)

    data class PhotoBigger(
        override val albumId: Int?,
        override val id: Int?,
        override val title: String,
        val url: String
    ) : Photo(albumId, id, title)

    enum class PhotoType{
        PhotoBig,
        PhotoMin
    }
    companion object {
        fun getSmallPhoto(photoRequest: PhotoRequest): PhotoSmaller {
            return PhotoSmaller(
                photoRequest.albumId,
                photoRequest.id,
                photoRequest.title,
                photoRequest.thumbnailUrl
            )
        }

        fun getBigPhoto(photoRequest: PhotoRequest): PhotoBigger {
            return PhotoBigger(
                photoRequest.albumId,
                photoRequest.id,
                photoRequest.title,
                photoRequest.url
            )
        }
    }
}