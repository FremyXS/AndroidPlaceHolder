package com.example.androidplaceholder.data.models

import com.example.androidplaceholder.data.requests.UserRequest

sealed class UserDefault (
    open val id: Int?,
    open val name: String?,
    open val username: String?,
    open val email: String?,
    open val phone: String?,
    open val website: String?
){
    data class User(
        override val id: Int?,
        override val name: String?,
        override val username: String?,
        override val email: String?,
        override val phone: String?,
        override val website: String?,
    ) : UserDefault(id, name, username, email, phone, website)

    data class UserInfo(
        override val id: Int?,
        override val name: String?,
        override val username: String?,
        override val email: String?,
        override val phone: String?,
        override val website: String?,
        val photo: String
    ) : UserDefault(id, name, username, email, phone, website)

    companion object{
        fun getUser(userRequest: UserRequest): User {
            return User(
                userRequest.id,
                userRequest.name,
                userRequest.username,
                userRequest.email,
                userRequest.phone,
                userRequest.website
            )
        }

        fun getUserInfo(user: User, img: String): UserInfo {
            return UserInfo(
                user.id,
                user.name,
                user.username,
                user.email,
                user.phone,
                user.website,
                img
            )
        }
    }
}