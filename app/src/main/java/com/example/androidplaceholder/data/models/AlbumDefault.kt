package com.example.androidplaceholder.data.models

import com.example.androidplaceholder.data.requests.AlbumRequest

sealed class AlbumDefault(
    open val userId: Int?,
    open val id: Int?,
    open val title: String?
) {
    data class Album(
        override val userId: Int?,
        override val id: Int?,
        override val title: String?
    ) : AlbumDefault(userId, id, title)

    data class AlbumInfo(
        override val userId: Int?,
        override val id: Int?,
        override val title: String?,
        val img: String?,
        val userFullName: String?,
        val countPhotos: Int?
    ) : AlbumDefault(userId, id, title)

    companion object {
        fun getAlbum(albumRequest: AlbumRequest): Album {
            return Album(
                albumRequest.userId,
                albumRequest.id,
                albumRequest.title
            )
        }
    }
}