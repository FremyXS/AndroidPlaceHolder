package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.Comment

interface CommentsRepository {
    suspend fun getComments() : List<Comment>

    suspend fun getCommentsByPostId(postId: Int) : List<Comment>
}