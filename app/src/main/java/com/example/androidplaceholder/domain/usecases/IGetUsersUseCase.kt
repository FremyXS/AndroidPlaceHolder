package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.UserDefault

interface IGetUsersUseCase {
    suspend operator fun invoke(): List<UserDefault.UserInfo>
}