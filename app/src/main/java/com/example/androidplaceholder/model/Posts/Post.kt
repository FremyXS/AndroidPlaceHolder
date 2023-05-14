package com.example.androidplaceholder.model.Posts

data class Post(
    val userId: Int?,
    val userFullName: String?,
    val id: Int?,
    val title: String?,
    val body: String?,
    val countComments: Int?
)