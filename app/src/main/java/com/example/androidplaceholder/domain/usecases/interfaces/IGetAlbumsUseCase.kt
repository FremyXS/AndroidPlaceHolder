package com.example.androidplaceholder.domain.usecases.interfaces

import com.example.androidplaceholder.data.models.AlbumDefault

interface IGetAlbumsUseCase {
    suspend operator fun invoke(): MutableList<AlbumDefault.AlbumInfo>
}