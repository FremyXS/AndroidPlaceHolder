package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.User
import com.example.androidplaceholder.data.remote.RetrofitService
import javax.inject.Inject

class UsersRepository
    @Inject constructor(private val retrofitService: RetrofitService) : IUsersRepository{
    override suspend fun getUsers(): List<User> {
        TODO("Not yet implemented")
    }

    override suspend fun getUserById(id: Int): User {
        TODO("Not yet implemented")
    }
}