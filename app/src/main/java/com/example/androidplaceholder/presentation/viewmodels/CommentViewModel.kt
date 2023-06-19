package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.domain.usecases.interfaces.IGetCommentsByPostIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentViewModel @Inject constructor(private val useCase : IGetCommentsByPostIdUseCase): ViewModel() {
    private val commentsListLiveData = MutableLiveData<List<Comment>>()

    fun getCommentsList(): MutableLiveData<List<Comment>> {

        return commentsListLiveData
    }

    fun setCommentsList(postId: Int){

        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(postId)
            }
            commentsListLiveData.postValue(result)
        }
    }
}