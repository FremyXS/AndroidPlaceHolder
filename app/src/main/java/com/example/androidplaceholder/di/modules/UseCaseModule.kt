package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.domain.usecases.*
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun getPostsUseCase(useCase: GetPostsUseCase): IGetPostsUseCase

    @Binds
    abstract fun getCommentsByPostIdUseCase(useCase: GetCommentsByPostIdUseCase): IGetCommentsByPostIdUseCase

    @Binds
    abstract fun getAlbumsUseCase(useCase: GetAlbumsUseCase): IGetAlbumsUseCase
}