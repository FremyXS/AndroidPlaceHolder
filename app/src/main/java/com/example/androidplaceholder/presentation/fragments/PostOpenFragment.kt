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
import com.example.androidplaceholder.databinding.FragmentPostOpenBinding
import com.example.androidplaceholder.presentation.adapters.CommentsContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.CommentViewModel

class PostOpenFragment : Fragment() {

    private lateinit var bind: FragmentPostOpenBinding
    private lateinit var commentsContainerAdapter: CommentsContainerAdapter
    private lateinit var commentViewModel: CommentViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        bind = FragmentPostOpenBinding.inflate(inflater, container, false)

        commentViewModel = ViewModelProvider(this).get(CommentViewModel::class.java)
        commentsContainerAdapter = CommentsContainerAdapter()

        bindAdapter()

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.userName.text = arguments?.getString("user")
        bind.postTitle.text = arguments?.getString("post_title")
        bind.postBody.text = arguments?.getString("post_body")
        bind.postCountComments.text = arguments?.getInt("post_count_comments").toString().plus(" comments")

        bind.topBar.title = arguments?.getString("post_title")
        bind.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

    private fun bindAdapter(){
        commentViewModel.getCommentsList().observe(viewLifecycleOwner, Observer{
                comments -> commentsContainerAdapter.submitList(comments)
        })
        commentViewModel.setCommentsList(arguments?.getInt("post_id")!!)

        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        bind.commentsContainer.layoutManager = layoutManager

        bind.commentsContainer.adapter = commentsContainerAdapter
    }
}