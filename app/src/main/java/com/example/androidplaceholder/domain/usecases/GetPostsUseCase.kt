package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.domain.repositories.*
import javax.inject.Inject

class GetPostsUseCase
    @Inject
    constructor(
        private val postRepository: IPostsRepository,
        private val usersRepository: IUsersRepository,
        private val commentsRepository: ICommentsRepository
    ): IGetPostsUseCase{

    override suspend operator fun invoke(): MutableList<PostDefault.PostInfo> {
        val posts = postRepository.getPosts()
        val users = usersRepository.getUsers()
        val comments = commentsRepository.getComments()

        val infoPosts = mutableListOf<PostDefault.PostInfo>()

        for(post in posts){
            val infoPost = PostDefault.PostInfo(
                post.userId,
                post.id,
                post.title,
                post.body,
                users.firstOrNull { it.id == post.userId }!!.name,
                comments.filter { it.postId == post.id }.size
            )

            infoPosts.add(infoPost)
        }

        return infoPosts
    }
}