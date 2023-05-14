package com.example.androidplaceholder.view.fragments.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.databinding.FragmentCommentCardBinding
import com.example.androidplaceholder.model.Comments.Comment

class CommentsContainerAdapter: ListAdapter<Comment, RecyclerView.ViewHolder>(CommentViewHolder()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentCommentCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return CommentItem(bind)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as CommentItem).bind(getItem(position))
    }

    class CommentItem(val bind: FragmentCommentCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(comment: Comment) = with(bind){
            userName.text = comment.name
            commentBody.text = comment.body
            userEmail.text = comment.email
        }
    }

    class CommentViewHolder(): DiffUtil.ItemCallback<Comment>(){
        override fun areItemsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Comment, newItem: Comment): Boolean {
            return oldItem == newItem
        }

    }
}