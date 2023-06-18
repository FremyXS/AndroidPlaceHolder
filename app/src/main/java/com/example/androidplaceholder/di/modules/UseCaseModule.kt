package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.domain.usecases.*
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun getGetPostsUseCase(useCase: GetPostsUseCase): IGetPostsUseCase

    @Binds
    abstract fun getGetCommentsByPostIdUseCase(useCase: GetCommentsByPostIdUseCase): IGetCommentsByPostIdUseCase

    @Binds
    abstract fun getGetPostsByUserIdUseCase(useCase: GetPostsByUserIdUseCase): IGetPostsByUserIdUseCase

    @Binds
    abstract fun getGetUsersUseCase(useCase: GetUsersUseCase): IGetUsersUseCase
}