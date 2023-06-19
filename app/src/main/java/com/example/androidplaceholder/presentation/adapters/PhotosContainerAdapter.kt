package com.example.androidplaceholder.presentation.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.androidplaceholder.data.models.Photo
import com.example.androidplaceholder.databinding.FragmentPhotoBigCardBinding
import com.example.androidplaceholder.databinding.FragmentPhotoMinCardBinding


class PhotosContainerAdapter(private val listener: Listener) : ListAdapter<Photo.PhotoBigger, RecyclerView.ViewHolder>(
    PhotoViewHolder()
) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        return when(viewType){
            Photo.PhotoType.PhotoBig.ordinal -> {
                val bind = FragmentPhotoBigCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )

                return PhotoBigItem(bind)
            }
            Photo.PhotoType.PhotoMin.ordinal -> {
                val bind = FragmentPhotoMinCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )

                return PhotoMinItem(bind)
            }
            else -> {
                val bind = FragmentPhotoMinCardBinding.inflate(
                    LayoutInflater.from(parent.context),
                    parent, false
                )

                return PhotoMinItem(bind)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder.itemViewType){
            Photo.PhotoType.PhotoBig.ordinal -> (holder as PhotoBigItem).bind(getItem(position), holder.itemView.context, listener)
            Photo.PhotoType.PhotoMin.ordinal -> (holder as PhotoMinItem).bind(getItem(position), holder.itemView.context, listener)
        }

    }

    override fun getItemViewType(position: Int): Int {
        val temp = getItem(position)

        return if(position % 3 == 0){
            Photo.PhotoType.PhotoBig.ordinal
        } else{
            Photo.PhotoType.PhotoMin.ordinal
        }
    }
    class PhotoBigItem(private val bind: FragmentPhotoBigCardBinding) : RecyclerView.ViewHolder(bind.root){
        fun bind(photo: Photo.PhotoBigger, context: Context, listener: Listener) = with(bind){
            Glide.with(context)
                .load(photo.url)
                .into(imgPhoto)

            imgPhoto.setOnClickListener{
                listener.onClick(photo)
            }
        }
    }

    class PhotoMinItem(private val bind: FragmentPhotoMinCardBinding) : RecyclerView.ViewHolder(bind.root){
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