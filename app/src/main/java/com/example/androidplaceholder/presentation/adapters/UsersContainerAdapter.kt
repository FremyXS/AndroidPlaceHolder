package com.example.androidplaceholder.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.databinding.FragmentUserCardBinding

class UsersContainerAdapter(private val listener: Listener) : ListAdapter<UserDefault.User, RecyclerView.ViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentUserCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return UserItem(bind);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserItem).bind(getItem(position), listener)
    }

    class UserItem(val bind: FragmentUserCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(user: UserDefault.User, listener: Listener) = with(bind){
            userBt.setOnClickListener{
                listener.onClick(user)
            }
        }
    }

    class UserDiffUtil : DiffUtil.ItemCallback<UserDefault.User>(){
        override fun areItemsTheSame(oldItem: UserDefault.User, newItem: UserDefault.User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserDefault.User, newItem: UserDefault.User): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener{
        fun onClick(user: UserDefault.User)
    }
}