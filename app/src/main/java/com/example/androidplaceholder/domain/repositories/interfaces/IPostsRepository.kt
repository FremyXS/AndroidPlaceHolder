package com.example.androidplaceholder.domain.repositories.interfaces

import com.example.androidplaceholder.data.models.PostDefault

interface IPostsRepository {
    suspend fun getPosts(): List<PostDefault.Post>
}