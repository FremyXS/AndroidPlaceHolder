package com.example.androidplaceholder.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.databinding.FragmentProfileInfoCardBinding


class ProfileInfoAdapter: ListAdapter<ProfileInfo.ProfileInfoContacts, RecyclerView.ViewHolder>(ProfileInfoDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentProfileInfoCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )

        return  ProfileInfoContactsItem(bind)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as ProfileInfoContactsItem).bind(getItem(position))
    }

    class ProfileInfoContactsItem(val bind: FragmentProfileInfoCardBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(profileInfo: ProfileInfo.ProfileInfoContacts) = with(bind){
            bind.infoLabel.text = profileInfo.label
            bind.infoValue.text = profileInfo.value
        }
    }

    class ProfileInfoDiffUtil : DiffUtil.ItemCallback<ProfileInfo.ProfileInfoContacts>(){
        override fun areItemsTheSame(oldItem: ProfileInfo.ProfileInfoContacts, newItem: ProfileInfo.ProfileInfoContacts): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProfileInfo.ProfileInfoContacts, newItem: ProfileInfo.ProfileInfoContacts): Boolean {
            return oldItem == newItem
        }
    }
}