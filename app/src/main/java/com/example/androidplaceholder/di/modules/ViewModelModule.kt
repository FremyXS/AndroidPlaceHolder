package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.presentation.viewmodels.CommentViewModel
import com.example.androidplaceholder.presentation.viewmodels.PostViewModel
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
}