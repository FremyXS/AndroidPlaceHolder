package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidplaceholder.databinding.FragmentAlbumsContainerBinding
import com.example.androidplaceholder.di.DaggerAppComponent
import com.example.androidplaceholder.presentation.adapters.AlbumsContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.AlbumViewModel
import javax.inject.Inject

class AlbumsContainerFragment : Fragment() {

    private lateinit var bind: FragmentAlbumsContainerBinding
    private lateinit var albumsContainerAdapter: AlbumsContainerAdapter

    @Inject
    lateinit var albumContainerViewModel: AlbumViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerAppComponent.builder()
            .build()
        component.inject(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentAlbumsContainerBinding.inflate(inflater, container, false)

        albumsContainerAdapter = AlbumsContainerAdapter()

        bindAdapter()

        return bind.root
    }

    private fun bindAdapter() {
        albumContainerViewModel.getList().observe(viewLifecycleOwner, Observer { info ->
            albumsContainerAdapter.submitList(info)
        })

        albumContainerViewModel.setList()

        bind.container.layoutManager =
            GridLayoutManager(context, 2, GridLayoutManager.VERTICAL, false)

        bind.container.adapter = albumsContainerAdapter
    }
}