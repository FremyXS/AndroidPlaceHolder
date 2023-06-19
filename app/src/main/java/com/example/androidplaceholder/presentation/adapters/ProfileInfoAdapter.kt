package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.databinding.FragmentProfileAlbumCardBinding
import com.example.androidplaceholder.databinding.FragmentProfileInfoCardBinding
import com.example.androidplaceholder.databinding.FragmentProfilePostCardBinding


class ProfileInfoAdapter(private val listener: Listener): ListAdapter<ProfileInfo, RecyclerView.ViewHolder>(ProfileInfoDiffUtil()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when(viewType){
            ProfileInfo.ProfileInfoType.ProfileInfoContacts.ordinal ->{
                val bind = FragmentProfileInfoCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoContactsItem(bind)
            }
            ProfileInfo.ProfileInfoType.ProfileInfoPost.ordinal -> {
                val bind = FragmentProfilePostCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoPostItem(bind)
            }

            ProfileInfo.ProfileInfoType.ProfileInfoAlbum.ordinal -> {
                val bind = FragmentProfileAlbumCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoAlbumItem(bind)
            }

            else -> {
                val bind = FragmentProfileInfoCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent,
                    false
                )

                return  ProfileInfoContactsItem(bind)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType) {
            ProfileInfo.ProfileInfoType.ProfileInfoContacts.ordinal ->
                (holder as ProfileInfoContactsItem).bind(getItem(position) as ProfileInfo.ProfileInfoContacts)
            ProfileInfo.ProfileInfoType.ProfileInfoPost.ordinal ->
                (holder as ProfileInfoPostItem).bind(getItem(position) as ProfileInfo.ProfileInfoPost, listener)
            ProfileInfo.ProfileInfoType.ProfileInfoAlbum.ordinal ->
                (holder as ProfileInfoAlbumItem).bind(getItem(position) as ProfileInfo.ProfileInfoAlbum, holder.itemView.context, listener)
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when(getItem(position)){
            is ProfileInfo.ProfileInfoContacts -> ProfileInfo.ProfileInfoType.ProfileInfoContacts.ordinal
            is ProfileInfo.ProfileInfoPost -> ProfileInfo.ProfileInfoType.ProfileInfoPost.ordinal
            is ProfileInfo.ProfileInfoAlbum -> ProfileInfo.ProfileInfoType.ProfileInfoAlbum.ordinal
            else -> {
                -1
            }
        }
    }

    class ProfileInfoContactsItem(val bind: FragmentProfileInfoCardBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(profileInfo: ProfileInfo.ProfileInfoContacts) = with(bind){
            bind.infoLabel.text = profileInfo.label
            bind.infoValue.text = profileInfo.value
        }
    }

    class ProfileInfoPostItem(val bind: FragmentProfilePostCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(profileInfo: ProfileInfo.ProfileInfoPost, listener: Listener) = with(bind){
            bind.postTitle.text = profileInfo.title
            bind.postBody.text = profileInfo.body
            bind.postId.setOnClickListener{
                listener.onClick(profileInfo, ProfileInfo.ProfileInfoType.ProfileInfoPost)
            }
        }
    }

    class ProfileInfoAlbumItem(val bind: FragmentProfileAlbumCardBinding): RecyclerView.ViewHolder(bind.root){
        fun bind(profileInfo: ProfileInfo.ProfileInfoAlbum, context: Context, listener: Listener) = with(bind){
            albumTitleId.text = profileInfo.title

            Glide.with(context)
                .load(profileInfo.img)
                .placeholder(R.drawable.animated_loading)
                .into(albumImageId)

            albumBtId.setOnClickListener {
                listener.onClick(profileInfo, ProfileInfo.ProfileInfoType.ProfileInfoAlbum)
            }
        }
    }

    class ProfileInfoDiffUtil : DiffUtil.ItemCallback<ProfileInfo>(){
        override fun areItemsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: ProfileInfo, newItem: ProfileInfo): Boolean {
            return oldItem == newItem
        }
    }

    interface Listener {
        fun onClick(user: ProfileInfo, type: ProfileInfo.ProfileInfoType)
    }
}