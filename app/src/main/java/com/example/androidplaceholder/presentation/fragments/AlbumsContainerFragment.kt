package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.databinding.FragmentAlbumsContainerBinding
import com.example.androidplaceholder.di.DaggerAppComponent
import com.example.androidplaceholder.presentation.adapters.AlbumsContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.AlbumViewModel
import javax.inject.Inject

class AlbumsContainerFragment : Fragment(), AlbumsContainerAdapter.Listener {

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

        albumsContainerAdapter = AlbumsContainerAdapter(this)

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

    override fun onClick(album: AlbumDefault.AlbumInfo) {
        val bundle = Bundle()
        bundle.putInt("albumId", album.id!!)
        findNavController().navigate(R.id.action_albumsContainerFragment_to_photosContainerFragment, bundle)
    }
}