package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.domain.usecases.IGetPostsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PostViewModel @Inject constructor(private val useCase : IGetPostsUseCase): ViewModel() {
    private val postListLiveData: MutableLiveData<List<PostDefault.PostInfo>> = MutableLiveData<List<PostDefault.PostInfo>>()
    fun setList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke()
            }
            postListLiveData.postValue(result)
        }
    }

    fun getList(): MutableLiveData<List<PostDefault.PostInfo>> {
        return postListLiveData
    }

}