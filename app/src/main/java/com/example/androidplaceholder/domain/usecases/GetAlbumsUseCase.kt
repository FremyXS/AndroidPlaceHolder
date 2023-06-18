package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.domain.repositories.*
import javax.inject.Inject

class GetAlbumsUseCase
@Inject
constructor(
    private val albumRepository: IAlbumsRepository,
    private val userRepository: IUsersRepository,
    private val photoRepository: IPhotosRepository
) : IGetAlbumsUseCase {
    override suspend fun invoke(): MutableList<AlbumDefault.AlbumInfo> {
        val albums = albumRepository.getAlbums()
        val users = userRepository.getUsers()
        val photos = photoRepository.getPhotos()

        val infoAlbums = mutableListOf<AlbumDefault.AlbumInfo>()

        for (album in albums) {
            val infoAlbum = AlbumDefault.AlbumInfo(
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