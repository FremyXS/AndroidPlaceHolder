package com.example.androidplaceholder.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidplaceholder.model.Comments.Comment

class CommentViewModel: ViewModel() {
    private val commentsListLiveData = MutableLiveData<List<Comment>>()

    fun getCommentsList(): MutableLiveData<List<Comment>> {
        return commentsListLiveData
    }

    fun setCommentsList(commentsList: List<Comment>){
        commentsListLiveData.value = commentsList
    }
}