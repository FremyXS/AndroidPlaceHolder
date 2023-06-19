package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.data.remote.RetrofitService
import com.example.androidplaceholder.domain.repositories.interfaces.IUsersRepository
import javax.inject.Inject

class UsersRepository
    @Inject constructor(private val retrofitService: RetrofitService) : IUsersRepository {
    override suspend fun getUsers(): List<UserDefault.User> {
        val response = retrofitService.getUserList()
        if (response.isSuccessful) {

            val users = mutableListOf<UserDefault.User>()

            for (user in response.body()!!){
                users.add(UserDefault.getUser(user))
            }

            return users
        }
        return listOf()
    }

    override suspend fun getUserById(id: Int): UserDefault.User {
        val response = retrofitService.getUserById(id)
        return UserDefault.getUser(response.body()!!)
    }
}