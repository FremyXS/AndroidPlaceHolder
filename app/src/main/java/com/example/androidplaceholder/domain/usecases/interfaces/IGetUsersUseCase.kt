package com.example.androidplaceholder.domain.usecases.interfaces

import com.example.androidplaceholder.data.models.UserDefault

interface IGetUsersUseCase {
    suspend operator fun invoke(): List<UserDefault.UserInfo>
}