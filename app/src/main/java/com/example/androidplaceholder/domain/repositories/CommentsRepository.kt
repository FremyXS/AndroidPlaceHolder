package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.data.remote.RetrofitService
import com.example.androidplaceholder.domain.repositories.interfaces.ICommentsRepository
import javax.inject.Inject

class CommentsRepository
    @Inject constructor(private val retrofitService: RetrofitService) : ICommentsRepository {
    override suspend fun getComments(): List<Comment> {
        val response = retrofitService.getCommentList()
        if (response.isSuccessful) {
            return response.body()!!
        }
        return listOf()
    }

    override suspend fun getCommentsByPostId(postId: Int): List<Comment> {
        val response = retrofitService.getCommentListByPostId(postId)
        if (response.isSuccessful) {
            return response.body()!!
        }
        return listOf()
    }
}