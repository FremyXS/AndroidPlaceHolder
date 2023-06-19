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

    @Binds
    abstract fun getPhotosByAlbumIdUseCase(useCase: GetPhotosByAlbumIdUseCase): IGetPhotosByAlbumIdUseCase

    @Binds
    abstract fun getGetPostsByUserIdUseCase(useCase: GetPostsByUserIdUseCase): IGetPostsByUserIdUseCase

    @Binds
    abstract fun getGetUsersUseCase(useCase: GetUsersUseCase): IGetUsersUseCase
    @Binds
    abstract fun getGetAlbumsByUserIdUseCase(useCase: GetAlbumsByUserIdUseCase) : IGetAlbumsByUserIdUseCase
}