package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.example.androidplaceholder.databinding.FragmentAlbumsContainerBinding
import com.example.androidplaceholder.di.DaggerAppComponent
import com.example.androidplaceholder.presentation.adapters.AlbumsContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.AlbumViewModel
import javax.inject.Inject

class AlbumsContainerFragment : Fragment() {

    private lateinit var bind: FragmentAlbumsContainerBinding
    private lateinit var albumsContainerAdapter: AlbumsContainerAdapter

    @Inject
    lateinit var albumViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerAppComponent.builder()
            .build()
        component.inject(this)
    }
}