package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.data.remote.RetrofitService
import javax.inject.Inject

class CommentsRepository
    @Inject constructor(private val retrofitService: RetrofitService) : ICommentsRepository{
    override suspend fun getComments(): List<Comment> {
        TODO("Not yet implemented")
    }

    override suspend fun getCommentsByPostId(postId: Int): List<Comment> {
        TODO("Not yet implemented")
    }
}