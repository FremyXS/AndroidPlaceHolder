package com.example.androidplaceholder.view.fragments.containers

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.api.RetrofitClient
import com.example.androidplaceholder.api.interfaces.PostInterface
import com.example.androidplaceholder.databinding.FragmentPostsContainerBinding
import com.example.androidplaceholder.model.Posts.Post
import com.example.androidplaceholder.model.Posts.PostDao
import com.example.androidplaceholder.view.fragments.adapters.PostsContainerAdapter
import com.example.androidplaceholder.viewmodel.PostViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PostsContainerFragment : Fragment(), PostsContainerAdapter.Listener {

    private lateinit var bind: FragmentPostsContainerBinding
    private lateinit var postViewModel: PostViewModel
    private lateinit var postsContainerAdapter: PostsContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentPostsContainerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        postsContainerAdapter = PostsContainerAdapter(this)
        postViewModel = ViewModelProvider(this).get(PostViewModel::class.java)
        bindAdapter()
        bind.container.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        bind.container.adapter = postsContainerAdapter


        return bind.root
    }

    private fun bindAdapter(){

        postViewModel.getList().observe(viewLifecycleOwner, Observer {
            postList -> postsContainerAdapter.submitList(postList)
        })


        val apiService = RetrofitClient.getRetrofit().create(PostInterface::class.java)
        val call = apiService.getPostList()

        call.enqueue(object : Callback<List<PostDao>> {
            override fun onResponse(call: Call<List<PostDao>>, response: Response<List<PostDao>>) {
                val posts = response.body()
                if(posts != null){
                    postViewModel.setList(posts)
                }
            }

            override fun onFailure(call: Call<List<PostDao>>, t: Throwable) {
                Log.e(ContentValues.TAG, "Error: ${t.message}")
            }
        })
    }

    override fun onClick(post: Post) {
        val bundle = Bundle()
        bundle.putString("user", post.userFullName)
        bundle.putString("post_id", post.id.toString())
        bundle.putString("post_title", post.title)
        bundle.putString("post_body", post.body)
        bundle.putString("post_count_comments", post.countComments.toString())

        findNavController().navigate(R.id.action_postsContainerFragment_to_postOpenFragment, bundle)
    }
}