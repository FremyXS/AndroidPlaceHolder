package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.domain.repositories.ICommentsRepository
import javax.inject.Inject

class GetCommentsByPostIdUseCase
    @Inject
    constructor(
        private val commentsRepository: ICommentsRepository
    ): IGetCommentsByPostIdUseCase{

    override suspend operator fun invoke(idPost: Int): List<Comment> {
        return commentsRepository.getCommentsByPostId(idPost)
    }
}