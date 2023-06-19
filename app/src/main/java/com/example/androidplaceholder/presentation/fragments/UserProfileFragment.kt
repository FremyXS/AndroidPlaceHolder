package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.databinding.FragmentUserProfileBinding
import com.example.androidplaceholder.di.DaggerAppComponent
import com.example.androidplaceholder.presentation.adapters.ProfileInfoAdapter
import com.example.androidplaceholder.presentation.viewmodels.ProfileViewModel
import com.google.android.material.tabs.TabLayout
import javax.inject.Inject


class UserProfileFragment : Fragment(), ProfileInfoAdapter.Listener {

    private lateinit var bind: FragmentUserProfileBinding
    private lateinit var profileInfoAdapter: ProfileInfoAdapter

    @Inject
    lateinit var profileViewModel: ProfileViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val component = DaggerAppComponent.builder()
            .build()
        component.inject(this)
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

        profileInfoAdapter = ProfileInfoAdapter(this)

        bindAdapter()
        onClickMenu()

        return bind.root
    }

    private fun bindAdapter(){
        profileViewModel.getList().observe(viewLifecycleOwner, Observer {
            info -> profileInfoAdapter.submitList(info)
        })

        profileViewModel.getCurrent().observe(viewLifecycleOwner, Observer {
            profileInfoAdapter.submitList(profileViewModel.getList().value)
        })

        profileViewModel.init(
            arguments?.getInt("userId")!!,
            arguments?.getString("userEmail")!!,
            arguments?.getString("userPhone")!!,
            arguments?.getString("userWeb")!!)

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
                    2 -> profileViewModel.setCurrentAlbums()
                    else -> profileViewModel.setCurrentPosts()
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab) {

            }

            override fun onTabReselected(tab: TabLayout.Tab) {

            }

        })

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        bind.userName.text = arguments?.getString("userFullName")
        bind.fullName.text = arguments?.getString("userName")
        bind.topBar.title = arguments?.getString("userFullName")
        Glide.with(bind.root).load(arguments?.getString("userPhoto")).into(bind.userPhoto)

        bind.topBar.setNavigationOnClickListener {
            findNavController().popBackStack()
        }


    }

    override fun onClick(info: ProfileInfo, type: ProfileInfo.ProfileInfoType) {
        val bundle = Bundle()

        when(type){
            ProfileInfo.ProfileInfoType.ProfileInfoPost ->{
                val temp: ProfileInfo.ProfileInfoPost = info as ProfileInfo.ProfileInfoPost
                bundle.putString("user", temp.userFullName)
                bundle.putInt("post_id", temp.id!!)
                bundle.putString("post_title", temp.title)
                bundle.putString("post_body", temp.body)
                bundle.putInt("post_count_comments", temp.countComments!!)

                findNavController().navigate(R.id.action_userProfileFragment_to_postOpenFragment, bundle)
            }
            ProfileInfo.ProfileInfoType.ProfileInfoAlbum -> {
                val temp: ProfileInfo.ProfileInfoAlbum = info as ProfileInfo.ProfileInfoAlbum
                bundle.putInt("albumId", temp.id!!)
                bundle.putString("albumUserFullName", temp.userFullName)
                bundle.putString("albumTitle", temp.title)
                findNavController().navigate(R.id.action_albumsContainerFragment2_to_photosContainerFragment, bundle)
            }
            else -> {}
        }

    }

}