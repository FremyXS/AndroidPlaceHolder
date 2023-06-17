package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.example.androidplaceholder.R
import com.example.androidplaceholder.databinding.FragmentUserProfileBinding


class UserProfileFragment : Fragment() {

    private lateinit var bind: FragmentUserProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentUserProfileBinding.inflate(
            inflater,
            container,
            false
        )
        // Inflate the layout for this fragment
        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bind.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }
    }

}