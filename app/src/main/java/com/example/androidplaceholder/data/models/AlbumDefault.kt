package com.example.androidplaceholder.data.models

sealed class AlbumDefault(
    open val userId: Int?,
    open val id: Int?,
    open val title: String
) {
    data class Album(
        override val userId: Int?,
        override val id: Int?,
        override val title: String
    ) : AlbumDefault(userId, id, title)

    data class AlbumInfo(
        override val userId: Int?,
        override val id: Int?,
        override val title: String,
        val img: String,
        val userFullName: String,
        val countPhotos: Int?
    ) : AlbumDefault(userId, id, title)
}