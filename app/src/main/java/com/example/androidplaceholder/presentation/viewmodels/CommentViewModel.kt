package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.domain.usecases.GetCommentsByPostIdUseCase
import com.example.androidplaceholder.domain.usecases.IGetCommentsByPostIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CommentViewModel @Inject constructor(private val useCase : IGetCommentsByPostIdUseCase): ViewModel() {
    private val commentsListLiveData = MutableLiveData<List<Comment>>()

//    private suspend fun getCommentListAsync(postId: Int): List<Comment>? {
//        val apiService = RetrofitClient.getRetrofit().create(CommentInterface::class.java)
//        val call = apiService.getCommentListByPostId(postId)
//
//        return try{
//            val res = call.awaitResponse()
//            res.body()
//        }catch (e: Exception) {
//            Log.e(ContentValues.TAG, "Error: ${e.message}")
//            listOf<Comment>()
//        }
//    }
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