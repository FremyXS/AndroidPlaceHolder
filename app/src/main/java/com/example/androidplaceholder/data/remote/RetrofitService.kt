package com.example.androidplaceholder.data.remote


import com.example.androidplaceholder.data.models.*
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("comments")
    suspend fun getCommentList(): List<Comment>

    @GET("comments")
    suspend fun getCommentListByPostId(@Query("postId") id: Int): List<Comment>

    @GET("posts")
    suspend fun getPostList(): List<PostDefault.Post>

    @GET("users")
    suspend fun getUserList(): List<UserDefault.User>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): UserDefault.User
}