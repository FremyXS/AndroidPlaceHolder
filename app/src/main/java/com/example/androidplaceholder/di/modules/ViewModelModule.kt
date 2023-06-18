package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.presentation.viewmodels.CommentViewModel
import com.example.androidplaceholder.presentation.viewmodels.PostViewModel
import com.example.androidplaceholder.presentation.viewmodels.ProfileViewModel
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
    abstract fun getCommentViewModel(commentViewModelViewModel: CommentViewModel) : CommentViewModel

    @Binds
    @Singleton
    abstract fun getProfileViewModel(profileViewModel: ProfileViewModel): ProfileViewModel
}