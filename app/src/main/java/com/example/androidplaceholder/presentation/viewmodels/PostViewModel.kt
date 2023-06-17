package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.domain.usecases.GetPostsUseCase
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
    //    private suspend fun getComments(): List<Comment>? {
    //        val apiService = RetrofitClient.getRetrofit().create(CommentInterface::class.java)
    //        val call = apiService.getCommentList()
    //
    //        return try{
    //            val res = call.awaitResponse()
    //            res.body()
    //        }catch (e: Exception) {
    //            Log.e(ContentValues.TAG, "Error: ${e.message}")
    //            listOf<Comment>()
    //        }
    //    }
    //
    //    private suspend fun getUserName(): List<UserDao>? {
    //        val apiService = RetrofitClient.getRetrofit().create(UserInterface::class.java)
    //        val call = apiService.getUserList()
    //
    //        return try{
    //            val res = call.awaitResponse()
    //            res.body()
    //        }catch (e: Exception) {
    //            Log.e(ContentValues.TAG, "Error: ${e.message}")
    //            listOf<UserDao>()
    //        }
    //    }
    //
    //    private suspend fun getPostList(): List<PostDao>? {
    //        val apiService = RetrofitClient.getRetrofit().create(PostInterface::class.java)
    //        val call = apiService.getPostList()
    //
    //        return try{
    //            val res = call.awaitResponse()
    //            res.body()
    //        }catch (e: Exception) {
    //            Log.e(ContentValues.TAG, "Error: ${e.message}")
    //            listOf<PostDao>()
    //        }
    //    }
    //    fun setList(postsList: List<PostDao>? = null){
    //
    //        viewModelScope.launch {
    //            var newPostList: List<PostDao>? = null
    //            val newPostsList: MutableList<Post> = mutableListOf()
    //            val usersList = async { getUserName() }
    //            val commentList = async { getComments() }
    //            if(postsList == null){
    //                newPostList = (async { getPostList() }).await()
    //            }
    //            else{
    //                newPostList = postsList
    //            }
    //
    //            for (post in newPostList!!){
    //                val newPost = Post(
    //                    post.id,
    //                    (usersList.await())!!.filter { it.id == post.userId }.firstOrNull()!!.name,
    //                    post.id,
    //                    post.title,
    //                    post.body,
    //                    (commentList.await())!!.filter { it.postId == post.id }.size
    //                )
    //                newPostsList.add(newPost)
    //            }
    //
    //            postListLiveData.value = newPostsList
    //        }
    //    }
    //
        fun getList(): MutableLiveData<List<PostDefault.PostInfo>> {
            return postListLiveData
        }

}