package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.domain.repositories.*
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun getPostsRepository(repository: PostsRepository): IPostsRepository

    @Binds
    @Singleton
    abstract fun getCommentsRepository(repository: CommentsRepository): ICommentsRepository

    @Binds
    @Singleton
    abstract fun getUsersRepository(repository: UsersRepository): IUsersRepository
}