package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.domain.repositories.*
import javax.inject.Inject

class GetAlbumsUseCase
@Inject
constructor(
    private val albumRepository: IAlbumsRepository,
    private val userRepository: IUsersRepository,
) : IGetAlbumsUseCase {
    override suspend fun invoke(): MutableList<AlbumDefault.AlbumInfo> {
        val albums = albumRepository.getAlbums()
        val users = userRepository.getUsers()

        val infoAlbums = mutableListOf<AlbumDefault.AlbumInfo>()

        for (album in albums) {
            val infoAlbum = AlbumDefault.AlbumInfo(
                album.userId,
                album.id,
                album.title,
                users.firstOrNull { it.id == album.userId }!!.name,
                100
                )
            infoAlbums.add(infoAlbum)
        }
        return infoAlbums
    }
}