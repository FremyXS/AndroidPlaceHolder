package com.example.androidplaceholder.data.models

sealed class UserDefault (
    open val id: Int?,
    open val name: String?,
    open val username: String?,
    open val email: String?,
){
    data class User(
        override val id: Int?,
        override val name: String?,
        override val username: String?,
        override val email: String?,
    ) : UserDefault(id, name, username, email)
}