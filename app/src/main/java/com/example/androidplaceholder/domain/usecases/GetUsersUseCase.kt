package com.example.androidplaceholder.domain.usecases

import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.domain.repositories.IUsersRepository
import javax.inject.Inject

class GetUsersUseCase
    @Inject constructor(
        private val usersRepository: IUsersRepository,
    ) : IGetUsersUseCase {
    private val imgs = listOf<String>(
        "https://hariane.com/file/2022/07/Ramalan-zodiak-Capricorn-Aquarius-Pisces-1-Agustus-2022-.webp",
        "https://baomoi-photo-fbcrawler.zadn.vn/w720x480/2020_05_27_304_35180440/d1030b3f5e7cb722ee6d.jpg",
        "https://rusdate.net/photos/134/3134/5113134/6m4vuboun8.jpg",
        "https://img2.goodfon.ru/wallpaper/nbig/a/b1/chiara-carrozzo-chiara-briunetka-model-light-bliki-portret-v.jpg",
        "https://webpulse.imgsmail.ru/imgpreview?mb=webpulse&key=pulse_cabinet-image-95d7c637-0d60-410a-9ab9-f82626de7ec8"
    )
    override suspend fun invoke(): List<UserDefault.UserInfo> {
        val usersInfo = mutableListOf<UserDefault.UserInfo>()
        val users = usersRepository.getUsers()

        for(user in users){
            usersInfo.add(
                UserDefault.getUserInfo(user, imgs[user.id!! % imgs.size])
            )
        }
        return usersInfo
    }
}