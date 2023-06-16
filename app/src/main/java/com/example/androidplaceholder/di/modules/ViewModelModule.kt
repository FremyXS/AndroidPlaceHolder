package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.presentation.viewmodels.CommentViewModel
import com.example.androidplaceholder.presentation.viewmodels.PostViewModel
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun getPostViewModel(postViewModel: PostViewModel) : PostViewModel

    @Binds
    abstract fun getCommentViewModel(commentViewModelViewModel: CommentViewModel) : CommentViewModel
}