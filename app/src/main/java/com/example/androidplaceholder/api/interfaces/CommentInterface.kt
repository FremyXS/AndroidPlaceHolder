package com.example.androidplaceholder.api.interfaces

import com.example.androidplaceholder.model.Comments.Comment
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface CommentInterface {
    @GET("comments")
    fun getCommentList(): Call<List<Comment>>

    @GET("comments")
    fun getCommentListByPostId(@Query("postId") id: Int): Call<List<Comment>>
}