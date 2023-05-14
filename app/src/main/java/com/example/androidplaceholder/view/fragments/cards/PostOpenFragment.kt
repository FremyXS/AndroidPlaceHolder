package com.example.androidplaceholder.view.fragments.cards

import android.content.ContentValues
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.api.RetrofitClient
import com.example.androidplaceholder.api.interfaces.CommentInterface
import com.example.androidplaceholder.databinding.FragmentPostOpenBinding
import com.example.androidplaceholder.model.Comments.Comment
import com.example.androidplaceholder.model.Posts.Post
import com.example.androidplaceholder.view.fragments.adapters.CommentsContainerAdapter
import com.example.androidplaceholder.viewmodel.CommentViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

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

//        var list: MutableList<Comment> = mutableListOf()
//
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//        list.add(Comment(1, 1, "One User", "one@email.com", "asfa;ksf;sajflk;sjafl;ksjaflja;ljsflksajfajsflksa"))
//
//        commentsContainerAdapter.submitList(list)

        val layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        bind.commentsContainer.layoutManager = layoutManager

        bind.commentsContainer.adapter = commentsContainerAdapter

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.userName.text = arguments?.getString("user")
        bind.postTitle.text = arguments?.getString("post_title")
        bind.postBody.text = arguments?.getString("post_body")
        bind.postCountComments.text = arguments?.getString("post_count_comments").plus(" comments")
    }

    private fun bindAdapter(){
        commentViewModel.getCommentsList().observe(viewLifecycleOwner, Observer{
            comments -> commentsContainerAdapter.submitList(comments)
        })

        val apiService = RetrofitClient.getRetrofit().create(CommentInterface::class.java)
        val call = apiService.getCommentListByPostId((arguments?.getString("post_id")!!).toInt())

        call.enqueue(object : Callback<List<Comment>> {
            override fun onResponse(call: Call<List<Comment>>, response: Response<List<Comment>>) {
                val comments = response.body()
                if(comments != null){
                    commentViewModel.setCommentsList(comments)
                }
            }

            override fun onFailure(call: Call<List<Comment>>, t: Throwable) {
                Log.e(ContentValues.TAG, "Error: ${t.message}")
            }
        })
    }
}