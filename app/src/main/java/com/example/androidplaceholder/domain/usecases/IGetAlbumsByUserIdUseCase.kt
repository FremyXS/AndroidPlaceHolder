package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.data.models.ProfileInfo

interface IGetAlbumsByUserIdUseCase {
    suspend operator fun invoke(userId: Int): MutableList<ProfileInfo.ProfileInfoAlbum>
}