package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Post

interface IPostsRepository {
    suspend fun getPosts(): List<Post>
}