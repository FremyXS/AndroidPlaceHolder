package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.domain.repositories.ICommentsRepository
import com.example.androidplaceholder.domain.repositories.IPostsRepository
import com.example.androidplaceholder.domain.repositories.IUsersRepository
import javax.inject.Inject

class GetPostsByUserIdUseCase
    @Inject constructor(
        private val postRepository: IPostsRepository,
        private val usersRepository: IUsersRepository,
        private val commentsRepository: ICommentsRepository
    ): IGetPostsByUserIdUseCase {
    override suspend fun invoke(userId: Int): MutableList<ProfileInfo.ProfileInfoPost> {
        val posts = postRepository.getPosts()
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

        infoPosts = infoPosts.filter { it.userId == userId }.toMutableList()

        return infoPosts
    }
}