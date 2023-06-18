package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidplaceholder.data.models.UserDefault

class UsersContainerViewModel: ViewModel() {
    private val usersLiveData = MutableLiveData<List<UserDefault.User>>()

    fun getUsers() = usersLiveData

    fun init(){
        val users = mutableListOf<UserDefault.User>()

        for (i in 0..10) {
            val user = UserDefault.User(
                i,
                "User",
                "User",
                "User",
                "+7(888)-555-35-35",
                "user@hotass.org"
            )

            users.add(user)
        }

        usersLiveData.postValue(users)
    }
}