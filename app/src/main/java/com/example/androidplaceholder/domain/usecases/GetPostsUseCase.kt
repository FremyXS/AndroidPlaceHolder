package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.domain.repositories.ICommentsRepository
import com.example.androidplaceholder.domain.repositories.IPostsRepository
import com.example.androidplaceholder.domain.repositories.IUsersRepository
import javax.inject.Inject

class GetPostsUseCase
    @Inject
    constructor(
        private val postRepository: IPostsRepository,
        private val usersRepository: IUsersRepository,
        private val commentsRepository: ICommentsRepository
    ){

    suspend operator fun invoke(): MutableList<PostDefault.PostInfo> {
        val posts = postRepository.getPosts()
        val users = usersRepository.getUsers()
        val comments = commentsRepository.getComments()

        val infoPosts: MutableList<PostDefault.PostInfo> = mutableListOf()

        for(post in posts){
            val infoPost = PostDefault.PostInfo(
                post.id,
                users.filter { it.id == post.userId }.firstOrNull()!!.name,
                post.id,
                post.title,
                post.body,
                comments.filter { it.postId == post.id }.size
            )

            infoPosts.add(infoPost)
        }

        return infoPosts
    }
}