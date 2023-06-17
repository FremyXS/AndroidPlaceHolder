package com.example.androidplaceholder.data.remote


import com.example.androidplaceholder.data.models.*
import com.example.androidplaceholder.data.requests.PostRequest
import com.example.androidplaceholder.data.requests.UserRequest
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("comments")
    suspend fun getCommentList(): Response<List<Comment>>

    @GET("comments")
    suspend fun getCommentListByPostId(@Query("postId") id: Int): Response<List<Comment>>

    @GET("posts")
    suspend fun getPostList(): Response<List<PostRequest>>

    @GET("users")
    suspend fun getUserList(): Response<List<UserRequest>>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): Response<UserRequest>
}