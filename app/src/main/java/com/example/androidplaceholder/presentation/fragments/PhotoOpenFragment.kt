package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.bumptech.glide.Glide
import com.example.androidplaceholder.R
import com.example.androidplaceholder.databinding.FragmentPhotoOpenBinding

class PhotoOpenFragment : Fragment() {

    private lateinit var bind: FragmentPhotoOpenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentPhotoOpenBinding.inflate(
            inflater,
            container,
            false
        )


        // Inflate the layout for this fragment
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(bind.root)
            .load(arguments?.getString("photoUrl")!!)
            .into(bind.photoImgId)

        bind.photoTitleId.text = arguments?.getString("photoTitle")!!
        bind.photoUserId.text = arguments?.getString("photoUserFullName")!!

    }
}