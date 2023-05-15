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

        return bind.root
    }

    private fun bindAdapter(){

        postViewModel.getList().observe(viewLifecycleOwner, Observer {
            postList -> postsContainerAdapter.submitList(postList)
        })

        postViewModel.setList()

        bind.container.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        bind.container.adapter = postsContainerAdapter
    }

    override fun onClick(post: Post) {
        val bundle = Bundle()
        bundle.putString("user", post.userFullName)
        bundle.putInt("post_id", post.id!!)
        bundle.putString("post_title", post.title)
        bundle.putString("post_body", post.body)
        bundle.putInt("post_count_comments", post.countComments!!)

        findNavController().navigate(R.id.action_postsContainerFragment_to_postOpenFragment, bundle)
    }
}