package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.domain.usecases.GetCommentsByPostIdUseCase
import com.example.androidplaceholder.domain.usecases.GetPostsUseCase
import com.example.androidplaceholder.domain.usecases.IGetCommentsByPostIdUseCase
import com.example.androidplaceholder.domain.usecases.IGetPostsUseCase
import dagger.Binds
import dagger.Module

@Module
abstract class UseCaseModule {
    @Binds
    abstract fun getGetPostsUseCase(useCase: GetPostsUseCase): IGetPostsUseCase

    @Binds
    abstract fun getGetCommentsByPostIdUseCase(useCase: GetCommentsByPostIdUseCase): IGetCommentsByPostIdUseCase
}