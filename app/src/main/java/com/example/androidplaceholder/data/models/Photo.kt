package com.example.androidplaceholder.data.models

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
}