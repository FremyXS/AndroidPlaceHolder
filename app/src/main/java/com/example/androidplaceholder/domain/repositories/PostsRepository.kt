package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Post
import com.example.androidplaceholder.data.remote.RetrofitService
import javax.inject.Inject

class PostsRepository
    @Inject constructor(private val retrofitService: RetrofitService) : IPostsRepository {
    override suspend fun getPosts(): List<Post> {
        val posts = retrofitService.getPostList()

        return posts
    }
}