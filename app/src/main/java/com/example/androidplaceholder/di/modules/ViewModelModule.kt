package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.presentation.viewmodels.AlbumViewModel
import com.example.androidplaceholder.presentation.viewmodels.CommentViewModel
import com.example.androidplaceholder.presentation.viewmodels.PhotoViewModel
import com.example.androidplaceholder.presentation.viewmodels.PostViewModel
import com.example.androidplaceholder.presentation.viewmodels.ProfileViewModel
import com.example.androidplaceholder.presentation.viewmodels.UsersContainerViewModel
import dagger.Binds
import dagger.Module
import javax.inject.Singleton

@Module
abstract class ViewModelModule {
    @Binds
    @Singleton
    abstract fun getPostViewModel(postViewModel: PostViewModel) : PostViewModel

    @Binds
    @Singleton
    abstract fun getCommentViewModel(commentViewModel: CommentViewModel) : CommentViewModel

    @Binds
    @Singleton
    abstract fun getAlbumViewModel(albumViewModel: AlbumViewModel): AlbumViewModel

    @Binds
    @Singleton
    abstract fun getPhotoViewModel(photoViewModel: PhotoViewModel): PhotoViewModel

    @Binds
    @Singleton
    abstract fun getProfileViewModel(profileViewModel: ProfileViewModel): ProfileViewModel

    @Binds
    @Singleton
    abstract fun getUsersContainerViewModel(usersContainerViewModel: UsersContainerViewModel): UsersContainerViewModel
}