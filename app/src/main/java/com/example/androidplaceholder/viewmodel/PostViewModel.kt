package com.example.androidplaceholder.viewmodel

import android.content.ContentValues
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.api.RetrofitClient
import com.example.androidplaceholder.api.interfaces.CommentInterface
import com.example.androidplaceholder.api.interfaces.PostInterface
import com.example.androidplaceholder.api.interfaces.UserInterface
import com.example.androidplaceholder.model.Comments.Comment
import com.example.androidplaceholder.model.Posts.Post
import com.example.androidplaceholder.model.Posts.PostDao
import com.example.androidplaceholder.model.Users.UserDao
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.awaitResponse

class PostViewModel() : ViewModel() {
    private val postListLiveData: MutableLiveData<List<Post>> = MutableLiveData<List<Post>>()

    private suspend fun getComments(): List<Comment>? {
        val apiService = RetrofitClient.getRetrofit().create(CommentInterface::class.java)
        val call = apiService.getCommentList()

        return try{
            val res = call.awaitResponse()
            res.body()
        }catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error: ${e.message}")
            listOf<Comment>()
        }
    }

    private suspend fun getUserName(): List<UserDao>? {
        val apiService = RetrofitClient.getRetrofit().create(UserInterface::class.java)
        val call = apiService.getUserList()

        return try{
            val res = call.awaitResponse()
            res.body()
        }catch (e: Exception) {
            Log.e(ContentValues.TAG, "Error: ${e.message}")
            listOf<UserDao>()
        }
    }

    fun setList(postsList: List<PostDao>){

        viewModelScope.launch {
            val newPostsList: MutableList<Post> = mutableListOf()
            val usersList = async { getUserName() }
            val commentList = async { getComments() }

            for (post in postsList){
                val newPost = Post(
                    post.id,
                    (usersList.await())!!.filter { it.id == post.userId }.firstOrNull()!!.name,
                    post.id,
                    post.title,
                    post.body,
                    (commentList.await())!!.filter { it.postId == post.id }.size
                )
                newPostsList.add(newPost)
            }

            postListLiveData.value = newPostsList
        }



    }

    fun getList(): LiveData<List<Post>> {
        return postListLiveData
    }

}