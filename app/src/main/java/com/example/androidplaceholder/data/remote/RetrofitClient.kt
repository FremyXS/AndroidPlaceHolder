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

class RetrofitClient {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService = retrofit.create(RetrofitService::class.java)

    suspend fun getCommentList(): List<Comment> {
        return retrofitService.getCommentList()
    }

    suspend fun getCommentListByPostId(id: Int): List<Comment> {
        return retrofitService.getCommentListByPostId(id)
    }

    suspend fun getPostList(): List<Post> {
        return retrofitService.getPostList()
    }

    suspend fun getUserList(): List<User> {
        return retrofitService.getUserList()
    }

    suspend fun getUserById(id: Int): User {
        return retrofitService.getUserById(id)
    }
}