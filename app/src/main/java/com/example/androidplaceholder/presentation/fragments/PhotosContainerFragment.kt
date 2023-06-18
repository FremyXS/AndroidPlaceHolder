package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.androidplaceholder.databinding.FragmentPhotoContainerBinding
import com.example.androidplaceholder.di.DaggerAppComponent
import com.example.androidplaceholder.presentation.adapters.PhotosContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.PhotoViewModel
import javax.inject.Inject

class PhotosContainerFragment : Fragment() {
    private lateinit var bind: FragmentPhotoContainerBinding
    private lateinit var photosContainerAdapter: PhotosContainerAdapter

    @Inject
    lateinit var photoContainerViewModel: PhotoViewModel

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
        bind = FragmentPhotoContainerBinding.inflate(inflater, container, false)

        photosContainerAdapter = PhotosContainerAdapter()

        bindAdapter()

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    private fun bindAdapter() {
        photoContainerViewModel.getList().observe(viewLifecycleOwner, Observer { photos ->
            photosContainerAdapter.submitList(photos)
        })

        photoContainerViewModel.setList(arguments?.getInt("album_id")!!)

        val layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        bind.container.layoutManager = layoutManager

        bind.container.adapter = photosContainerAdapter
    }
}