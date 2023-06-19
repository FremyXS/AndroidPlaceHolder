package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.data.remote.RetrofitService
import com.example.androidplaceholder.domain.repositories.interfaces.IPostsRepository
import javax.inject.Inject

class PostsRepository
    @Inject constructor(private val retrofitService: RetrofitService) : IPostsRepository {
    override suspend fun getPosts(): List<PostDefault.Post> {
        val response = retrofitService.getPostList()
        if (response.isSuccessful) {
            val posts = mutableListOf<PostDefault.Post>()

            for (post in response.body()!!){
                posts.add(PostDefault.getPost(post))
            }

            return posts
        }
        return listOf()
    }
}