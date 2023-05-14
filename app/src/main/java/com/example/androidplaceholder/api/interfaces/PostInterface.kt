package com.example.androidplaceholder.api.interfaces

import com.example.androidplaceholder.model.Posts.Post
import com.example.androidplaceholder.model.Posts.PostDao
import retrofit2.Call
import retrofit2.http.GET

interface PostInterface {
    @GET("posts")
    fun getPostList(): Call<List<PostDao>>
}