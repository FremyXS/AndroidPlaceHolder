package com.example.androidplaceholder.domain.usecases.interfaces

import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.data.models.ProfileInfo

interface IGetPostsByUserIdUseCase {
    suspend operator fun invoke(userId: Int): MutableList<ProfileInfo.ProfileInfoPost>
}