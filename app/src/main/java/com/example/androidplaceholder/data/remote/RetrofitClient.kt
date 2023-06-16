package com.example.androidplaceholder.data.remote


import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.data.models.Post
import com.example.androidplaceholder.data.models.User
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RetrofitService {
    @GET("comments")
    suspend fun getCommentList(): List<Comment>

    @GET("comments")
    suspend fun getCommentListByPostId(@Query("postId") id: Int): List<Comment>

    @GET("posts")
    suspend fun getPostList(): List<Post>

    @GET("users")
    suspend fun getUserList(): List<User>

    @GET("users/{id}")
    suspend fun getUserById(@Path("id") id: Int): User
}