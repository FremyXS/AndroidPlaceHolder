package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.domain.repositories.interfaces.ICommentsRepository
import com.example.androidplaceholder.domain.repositories.interfaces.IPostsRepository
import com.example.androidplaceholder.domain.repositories.interfaces.IUsersRepository
import com.example.androidplaceholder.domain.usecases.interfaces.IGetPostsByUserIdUseCase
import javax.inject.Inject

class GetPostsByUserIdUseCase
    @Inject constructor(
        private val postRepository: IPostsRepository,
        private val usersRepository: IUsersRepository,
        private val commentsRepository: ICommentsRepository
    ): IGetPostsByUserIdUseCase {
    override suspend fun invoke(userId: Int): MutableList<ProfileInfo.ProfileInfoPost> {
        var posts = postRepository.getPosts()
        posts = posts.filter { it -> it.userId == userId }
        val users = usersRepository.getUsers()
        val comments = commentsRepository.getComments()

        var infoPosts = mutableListOf<ProfileInfo.ProfileInfoPost>()

        for(post in posts){
            val infoPost = ProfileInfo.ProfileInfoPost(
                post.title!!,
                post.body!!,
                post.userId,
                post.id,
                users.filter { it.id == post.userId }.firstOrNull()!!.name,
                comments.filter { it.postId == post.id }.size
            )

            infoPosts.add(infoPost)
        }

        return infoPosts
    }
}