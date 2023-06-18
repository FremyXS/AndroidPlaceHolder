package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.databinding.FragmentAlbumCardBinding

class AlbumsContainerAdapter : ListAdapter<AlbumDefault.AlbumInfo, RecyclerView.ViewHolder>(
    PostDiffUtil()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentAlbumCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return AlbumItem(bind)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumItem).bind(getItem(position), holder.itemView.context)
    }

    class AlbumItem(private val bind: FragmentAlbumCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(album: AlbumDefault.AlbumInfo, context: Context) = with(bind) {
            titleAlbum.text = album.title
            Glide.with(context)
                .load(album.img)
                .into(imgAlbum)
            countPhotosAlbum.text = album.countPhotos.toString()
            userNameAlbum.text = album.userFullName
        }
    }

    class PostDiffUtil : DiffUtil.ItemCallback<AlbumDefault.AlbumInfo>(){
        override fun areItemsTheSame(oldItem: AlbumDefault.AlbumInfo, newItem: AlbumDefault.AlbumInfo): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: AlbumDefault.AlbumInfo, newItem: AlbumDefault.AlbumInfo): Boolean {
            return oldItem == newItem
        }
    }
}