package com.example.androidplaceholder.domain.repositories

import com.example.androidplaceholder.data.models.User

interface IUsersRepository {
    suspend fun getUsers(): List<User>

    suspend fun getUserById(id: Int): User
}