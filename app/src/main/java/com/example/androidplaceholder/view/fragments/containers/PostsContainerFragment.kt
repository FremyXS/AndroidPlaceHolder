package com.example.androidplaceholder.view.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.androidplaceholder.R
import com.example.androidplaceholder.databinding.FragmentPostsContainerBinding

class PostsContainerFragment : Fragment() {

    private lateinit var bind: FragmentPostsContainerBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentPostsContainerBinding.inflate(inflater, container, false)
        // Inflate the layout for this fragment
        return bind.root
    }
}