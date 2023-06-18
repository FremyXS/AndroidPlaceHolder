package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.databinding.FragmentUserCardBinding

class UsersContainerAdapter(private val listener: Listener) : ListAdapter<UserDefault.UserInfo, RecyclerView.ViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentUserCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return UserItem(bind);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserItem).bind(getItem(position), listener, holder.itemView.context)
    }

    class UserItem(val bind: FragmentUserCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(user: UserDefault.UserInfo, listener: Listener, context: Context) = with(bind){
            Glide.with(context).load(user.photo).into(userBt)

            userId.setOnClickListener{
                listener.onClick(user)
            }
        }
    }

    class UserDiffUtil : DiffUtil.ItemCallback<UserDefault.UserInfo>(){
        override fun areItemsTheSame(oldItem: UserDefault.UserInfo, newItem: UserDefault.UserInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserDefault.UserInfo, newItem: UserDefault.UserInfo): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener{
        fun onClick(user: UserDefault.UserInfo)
    }
}