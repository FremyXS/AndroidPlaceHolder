package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.data.remote.RetrofitService
import javax.inject.Inject

class UsersRepository
    @Inject constructor(private val retrofitService: RetrofitService) : IUsersRepository{
    override suspend fun getUsers(): List<UserDefault.User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(id: Int): UserDefault.User {
        TODO("Not yet implemented")
    }
}