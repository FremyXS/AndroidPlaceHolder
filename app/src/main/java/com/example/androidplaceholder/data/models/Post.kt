package com.example.androidplaceholder.data.models

sealed class PostDefault(
    open val userId: Int?,
    open val id: Int?,
    open val title: String?,
    open val body: String?,
){
    data class Post(
        override val userId: Int?,
        override val id: Int?,
        override val title: String?,
        override val body: String?,
    ) : PostDefault(userId, id, title, body)

    data class PostInfo(
        override val userId: Int?,
        override val id: Int?,
        override val title: String?,
        override val body: String?,
        val userFullName: String?,
        val countComments: Int?
    ) : PostDefault(userId, id, title, body)
}