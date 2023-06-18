package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.androidplaceholder.R
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.databinding.FragmentUsersContainerBinding
import com.example.androidplaceholder.di.DaggerAppComponent
import com.example.androidplaceholder.presentation.adapters.UsersContainerAdapter
import com.example.androidplaceholder.presentation.viewmodels.UsersContainerViewModel
import javax.inject.Inject

class UsersContainerFragment : Fragment(), UsersContainerAdapter.Listener {

    private lateinit var bind: FragmentUsersContainerBinding
    private lateinit var usersContainerAdapter: UsersContainerAdapter

    @Inject
    lateinit var usersContainerViewModel: UsersContainerViewModel

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
        bind = FragmentUsersContainerBinding.inflate(inflater, container, false)
        onClickMenu()
        usersContainerAdapter = UsersContainerAdapter(this)

        bindAdapter()

        return bind.root
    }

    private fun bindAdapter() {
        usersContainerViewModel.getUsers().observe(viewLifecycleOwner, Observer {
                info -> usersContainerAdapter.submitList(info)
        })

        usersContainerViewModel.init()

        val layout = GridLayoutManager(
            context,
            3,
            GridLayoutManager.VERTICAL,
            false
        )

        bind.container.layoutManager = layout

        bind.container.adapter = usersContainerAdapter
    }

    private fun onClickMenu(){
        bind.mainNavigation.selectedItemId = R.id.menu_profile_icon
        bind.mainNavigation.setOnItemSelectedListener{item->
            when(item.itemId){
                R.id.menu_post_icon -> {
                    findNavController().navigate(R.id.action_usersContainerFragment_to_postsContainerFragment, null)
                    true
                }
                else -> false
            }
        }
    }

    override fun onClick(user: UserDefault.UserInfo) {

        val bundle = Bundle()
        bundle.putInt("userId", user.id!!)
        bundle.putString("userFullName", user.name!!)
        bundle.putString("userName", user.username!!)
        bundle.putString("userEmail", user.email!!)
        bundle.putString("userPhone", user.phone!!)
        bundle.putString("userWeb", user.website!!)
        bundle.putString("userPhoto", user.photo)

        findNavController().navigate(R.id.action_usersContainerFragment_to_userProfileFragment2, bundle)
    }
}