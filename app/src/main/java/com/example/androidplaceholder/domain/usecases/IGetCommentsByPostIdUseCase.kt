package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.Comment

interface IGetCommentsByPostIdUseCase {
    suspend operator fun invoke(idPost: Int): List<Comment>
}