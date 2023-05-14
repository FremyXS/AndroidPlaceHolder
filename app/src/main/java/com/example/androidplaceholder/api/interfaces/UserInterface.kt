package com.example.androidplaceholder.api.interfaces

import com.example.androidplaceholder.model.Comments.Comment
import com.example.androidplaceholder.model.Users.UserDao
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface UserInterface {
    @GET("users")
    fun getUserList(): Call<List<UserDao>>

    @GET("users/{id}")
    fun getUserById(@Path("id") id: Int): Call<UserDao>
}