package com.example.androidplaceholder.presentation.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.databinding.FragmentUsersContainerBinding
import com.example.androidplaceholder.presentation.adapters.UsersContainerAdapter

class UsersContainerFragment : Fragment() {

    private lateinit var bind: FragmentUsersContainerBinding
    private lateinit var usersContainerAdapter: UsersContainerAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        bind = FragmentUsersContainerBinding.inflate(inflater, container, false)

        usersContainerAdapter = UsersContainerAdapter()

        val users = mutableListOf<UserDefault.User>()

        for (i in 0..10) {
            val user = UserDefault.User(
                i,
                "User",
                "User",
                "User",
            )

            users.add(user)
        }

        usersContainerAdapter.submitList(users)

        val layout = GridLayoutManager(
            context,
            3,
            GridLayoutManager.VERTICAL,
            false
        )



        bind.container.layoutManager = layout

        bind.container.adapter = usersContainerAdapter;

        return bind.root
    }
}