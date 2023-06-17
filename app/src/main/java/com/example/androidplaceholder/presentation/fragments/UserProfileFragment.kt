package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.databinding.FragmentUserProfileBinding
import com.example.androidplaceholder.presentation.adapters.ProfileInfoAdapter
import com.example.androidplaceholder.presentation.viewmodels.ProfileViewModel
import com.google.android.material.tabs.TabLayout


class UserProfileFragment : Fragment() {

    private lateinit var bind: FragmentUserProfileBinding
    private lateinit var profileInfoAdapter: ProfileInfoAdapter
    private lateinit var profileViewModel: ProfileViewModel
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

        profileViewModel = ViewModelProvider(this).get(ProfileViewModel::class.java)

        profileInfoAdapter = ProfileInfoAdapter()
        bindAdapter()

        return bind.root
    }

    private fun bindAdapter(){
        profileViewModel.getList().observe(viewLifecycleOwner, Observer {
            info -> profileInfoAdapter.submitList(info)
        })

        profileViewModel.init()

        bind.container.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.VERTICAL,
            false
        )

        bind.container.adapter = profileInfoAdapter
    }

    private fun onClickMenu(){

        bind.profileMenu.addOnTabSelectedListener(object :
            TabLayout.OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {
                val selectedTabIndex = tab.position

                when(selectedTabIndex){
                    0 -> profileViewModel.setCurrentContacts()
                    1 -> profileViewModel.setCurrentPosts()
                    2 -> profileViewModel.setCurrentPosts()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })
//        bind.menuContacts.setOnClickListener{
//            profileViewModel.setCurrentContacts()
//        }
//
//        bind.menuPosts.setOnClickListener{
//            profileViewModel.setCurrentPosts()
//        }
//
//        bind.menuPhotos.setOnClickListener{
//            profileViewModel.setCurrentContacts()
//        }
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
        bind.fullName.text = arguments?.getString("userName")
        bind.topBar.title = arguments?.getString("userFullName")

        bind.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }

        onClickMenu()

    }

}