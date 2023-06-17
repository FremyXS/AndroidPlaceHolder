package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.databinding.FragmentUserProfileBinding
import com.example.androidplaceholder.presentation.adapters.ProfileInfoAdapter


class UserProfileFragment : Fragment() {

    private lateinit var bind: FragmentUserProfileBinding
    private lateinit var profileInfoAdapter: ProfileInfoAdapter
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

        profileInfoAdapter = ProfileInfoAdapter()
        val contacts = mutableListOf<ProfileInfo.ProfileInfoContacts>()
        val temps = listOf("email", "phone", "website")
        val temps2 = listOf("hot@mail.com", "+7(999)-555-35-35", "poshel-nahuy")

        for (i in 0..2){
            contacts.add(ProfileInfo.ProfileInfoContacts(
                temps[i],
                temps2[i]
            ))
        }

        profileInfoAdapter.submitList(contacts)

        bind.container.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        bind.container.adapter = profileInfoAdapter

        return bind.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        bundle.putInt("userId", user.id!!)
//        bundle.putString("userFullName", user.name!!)
//        bundle.putString("userName", user.username!!)
//        bundle.putString("userEmail", user.email!!)


//        bind.userName.text = arguments?.getString("user")
//        bind.postTitle.text = arguments?.getString("post_title")
//        bind.postBody.text = arguments?.getString("post_body")
//        bind.postCountComments.text = arguments?.getInt("post_count_comments").toString().plus(" comments")
//
//        bind.topBar.title = arguments?.getString("post_title")

        bind.userName.text = arguments?.getString("userFullName")
        bind.fullName.text = arguments?.getString("userFullName")
        bind.topBar.title = arguments?.getString("userFullName")

        bind.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

    }

}