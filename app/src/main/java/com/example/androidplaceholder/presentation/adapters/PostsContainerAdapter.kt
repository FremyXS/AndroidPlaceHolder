package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.databinding.FragmentPostCardBinding

class PostsContainerAdapter(private val listener: Listener): ListAdapter<PostDefault.PostInfo, RecyclerView.ViewHolder>(
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

    class PostItem(private val bind: FragmentPostCardBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(post: PostDefault.PostInfo, context: Context, listener: Listener) = with(bind){
            titlePost.text = post.title
            bodyPost.text = post.body
            countCommentsPost.text = post.countComments.toString()
            userNamePost.text = post.userFullName
            navigateBt.setOnClickListener{
                listener.onClick(post)
            }
        }
    }

    class PostDiffUtil : DiffUtil.ItemCallback<PostDefault.PostInfo>(){
        override fun areItemsTheSame(oldItem: PostDefault.PostInfo, newItem: PostDefault.PostInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: PostDefault.PostInfo, newItem: PostDefault.PostInfo): Boolean {
            return oldItem == newItem
        }

    }

    interface Listener{
        fun onClick(post: PostDefault.PostInfo)
    }
}