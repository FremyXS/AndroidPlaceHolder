package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Comment

interface ICommentsRepository {
    suspend fun getComments() : List<Comment>

    suspend fun getCommentsByPostId(postId: Int) : List<Comment>
}