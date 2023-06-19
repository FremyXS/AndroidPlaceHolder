package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.domain.repositories.interfaces.IAlbumsRepository
import com.example.androidplaceholder.domain.repositories.interfaces.IPhotosRepository
import com.example.androidplaceholder.domain.repositories.interfaces.IUsersRepository
import com.example.androidplaceholder.domain.usecases.interfaces.IGetAlbumsByUserIdUseCase
import javax.inject.Inject

class GetAlbumsByUserIdUseCase
@Inject
constructor(
    private val albumRepository: IAlbumsRepository,
    private val userRepository: IUsersRepository,
    private val photoRepository: IPhotosRepository
): IGetAlbumsByUserIdUseCase {
    override suspend fun invoke(userId: Int): MutableList<ProfileInfo.ProfileInfoAlbum> {
        var albums = albumRepository.getAlbums()
        albums = albums.filter { it -> it.userId == userId }
        val users = userRepository.getUsers()
        val photos = photoRepository.getSmallPhotos()

        val infoAlbums = mutableListOf<ProfileInfo.ProfileInfoAlbum>()

        for (album in albums) {
            val infoAlbum = ProfileInfo.ProfileInfoAlbum(
                album.userId,
                album.id,
                album.title,
                photos.firstOrNull { it.albumId == album.id }!!.thumbnailUrl,
                users.firstOrNull { it.id == album.userId }!!.name,
                photos.filter { it.albumId == album.id }.size
            )

            infoAlbums.add(infoAlbum)
        }
        return infoAlbums
    }
}