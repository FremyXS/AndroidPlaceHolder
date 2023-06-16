package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.databinding.FragmentPostCardBinding
import com.example.androidplaceholder.model.Posts.Post

class PostsContainerAdapter(private val listener: Listener): ListAdapter<Post, RecyclerView.ViewHolder>(
    PostDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentPostCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return PostItem(bind)
    }

    override fun getItemViewType(position: Int): Int {
        return super.getItemViewType(position)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PostItem).bind(getItem(position), holder.itemView.context, listener)
    }

    class PostItem(val bind: FragmentPostCardBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(post: Post, context: Context, listener: Listener) = with(bind){
            titlePost.text = post.title
            bodyPost.text = post.body
            countCommentsPost.text = post.countComments.toString()
            userNamePost.text = post.userFullName
            navigateBt.setOnClickListener{
                listener.onClick(post)
            }
        }
    }

    class PostDiffUtil : DiffUtil.ItemCallback<Post>(){
        override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener{
        fun onClick(post: Post)
    }
}