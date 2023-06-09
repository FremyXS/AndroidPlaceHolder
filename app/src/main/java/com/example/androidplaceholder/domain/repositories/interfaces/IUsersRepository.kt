package com.example.androidplaceholder.domain.repositories.interfaces

import com.example.androidplaceholder.data.models.UserDefault

interface IUsersRepository {
    suspend fun getUsers(): List<UserDefault.User>

    suspend fun getUserById(id: Int): UserDefault.User
}