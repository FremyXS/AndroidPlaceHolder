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
    private val temps = listOf<String>(
        "https://schtirlitz.ru/800/600/http/ic.pics.livejournal.com/dymontiger/54234047/23584433/23584433_original.jpg",
        "https://funik.ru/wp-content/uploads/2018/10/ff514ad74c0c510849a1.jpg",
        "https://vsegda-pomnim.com/uploads/posts/2022-05/1651451712_60-vsegda-pomnim-com-p-samie-krasivie-vodopadi-rossii-foto-69.jpg",
        "https://otkrit-ka.ru/uploads/posts/2021-11/krasivye-foto-kartinki-ozero-13.jpg",
        "https://mykaleidoscope.ru/x/uploads/posts/2022-10/1666307115_54-mykaleidoscope-ru-p-zabroshennii-uchastok-pinterest-60.jpg",
        "https://drscdn.500px.org/photo/1281286/m%3D2048_k%3D1_a%3D1/v2?sig=7ff43b7406de914799488b00101aa75c91a7fa8964841b82b36db656d7fc411d",
        "https://img-fotki.yandex.ru/get/6825/127908635.386/0_fdfbc_34cfb266_orig.jpg",
        "https://mtdata.ru/u17/photo5971/20030323803-0/original.jpg",
        "https://phonoteka.org/uploads/posts/2021-07/1625670808_11-phonoteka-org-p-arti-zabroshennikh-mest-krasivo-12.jpg",
        "https://mykaleidoscope.ru/x/uploads/posts/2022-10/1666307128_8-mykaleidoscope-ru-p-zabroshennii-uchastok-pinterest-9.jpg"
    )
    override suspend fun invoke(): MutableList<AlbumDefault.AlbumInfo> {
        val albums = albumRepository.getAlbums()
        val users = userRepository.getUsers()
        val photos = photoRepository.getSmallPhotos()

        val infoAlbums = mutableListOf<AlbumDefault.AlbumInfo>()

        for (album in albums) {
            val infoAlbum = AlbumDefault.AlbumInfo(
                album.userId,
                album.id,
                album.title,
                temps[album.id!! % temps.size],
                users.firstOrNull { it.id == album.userId }!!.name,
                photos.filter { it.albumId == album.id }.size
            )

            infoAlbums.add(infoAlbum)
        }
        return infoAlbums
    }
}