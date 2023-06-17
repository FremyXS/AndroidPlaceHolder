package com.example.androidplaceholder.presentation.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
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

        return AlbumItem(bind);
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as AlbumItem).bind(getItem(position))
    }

    class AlbumItem(val bind: FragmentAlbumCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(album: AlbumDefault.AlbumInfo) = with(bind) {

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