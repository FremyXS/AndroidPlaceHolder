package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.databinding.FragmentPostsContainerBinding
import com.example.androidplaceholder.model.Posts.Post
import com.example.androidplaceholder.presentation.adapters.PostsContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.PostViewModel
import javax.inject.Inject

class PostsContainerFragment : Fragment(), PostsContainerAdapter.Listener {

    private lateinit var bind: FragmentPostsContainerBinding
    private lateinit var postsContainerAdapter: PostsContainerAdapter

    @Inject
    lateinit var postViewModel: PostViewModel

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
        postViewModel.setList()
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

    override fun onClick(post: PostDefault.PostInfo) {
        val bundle = Bundle()
        bundle.putString("user", post.userFullName)
        bundle.putInt("post_id", post.id!!)
        bundle.putString("post_title", post.title)
        bundle.putString("post_body", post.body)
        bundle.putInt("post_count_comments", post.countComments!!)

        findNavController().navigate(R.id.action_postsContainerFragment_to_postOpenFragment, bundle)
    }
}