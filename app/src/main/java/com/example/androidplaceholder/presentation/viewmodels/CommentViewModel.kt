package com.example.androidplaceholder.presentation.viewmodels

import android.content.ContentValues
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.api.RetrofitClient
import com.example.androidplaceholder.api.interfaces.CommentInterface
import com.example.androidplaceholder.model.Comments.Comment
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.awaitResponse

class CommentViewModel: ViewModel() {
    private val commentsListLiveData = MutableLiveData<List<Comment>>()

    private suspend fun getCommentListAsync(postId: Int): List<Comment>? {
        val apiService = RetrofitClient.getRetrofit().create(CommentInterface::class.java)
        val call = apiService.getCommentListByPostId(postId)

        return try{
            val res = call.awaitResponse()
            res.body()
        }catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error: ${e.message}")
            listOf<Comment>()
        }
    }
    fun getCommentsList(): MutableLiveData<List<Comment>> {

        return commentsListLiveData
    }

    fun setCommentsList(postId: Int){

        viewModelScope.launch {
            var newCommentList: List<Comment>? = null

            newCommentList = (async{getCommentListAsync(postId)}).await()

            commentsListLiveData.value = newCommentList!!
        }
    }
}