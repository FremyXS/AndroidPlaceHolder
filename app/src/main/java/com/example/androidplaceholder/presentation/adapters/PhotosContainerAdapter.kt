package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidplaceholder.data.models.Photo
import com.example.androidplaceholder.databinding.FragmentPhotoCardBinding


class PhotosContainerAdapter(private val listener: Listener) : ListAdapter<Photo.PhotoBigger, RecyclerView.ViewHolder>(
    PhotoViewHolder()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val bind = FragmentPhotoCardBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )

        return PhotoItem(bind)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PhotoItem).bind(getItem(position), holder.itemView.context, listener)
    }

    class PhotoItem(private val bind: FragmentPhotoCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(photo: Photo.PhotoBigger, context: Context, listener: Listener) = with(bind){
            Glide.with(context)
                .load(photo.url)
                .into(imgPhoto)

            imgPhoto.setOnClickListener{
                listener.onClick(photo)
            }
        }
    }

    interface Listener{
        fun onClick(photo: Photo.PhotoBigger)
    }

    class PhotoViewHolder: DiffUtil.ItemCallback<Photo.PhotoBigger>(){
        override fun areItemsTheSame(oldItem: Photo.PhotoBigger, newItem: Photo.PhotoBigger): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: Photo.PhotoBigger, newItem: Photo.PhotoBigger): Boolean {
            return oldItem == newItem
        }
    }
}