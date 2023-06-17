package com.example.androidplaceholder.presentation.adapters

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.data.models.UserDefault

class UsersContainerAdapter() : ListAdapter<UserDefault.User, RecyclerView.ViewHolder>(UserDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    class UserDiffUtil : DiffUtil.ItemCallback<UserDefault.User>(){
        override fun areItemsTheSame(oldItem: UserDefault.User, newItem: UserDefault.User): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: UserDefault.User, newItem: UserDefault.User): Boolean {
            return oldItem == newItem
        }

    }
}