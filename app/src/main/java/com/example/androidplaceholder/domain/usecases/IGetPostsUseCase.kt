package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.PostDefault

interface IGetPostsUseCase {
    suspend operator fun invoke(): MutableList<PostDefault.PostInfo>
}