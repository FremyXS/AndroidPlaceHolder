package com.example.androidplaceholder.data.models

sealed class ProfileInfo (){
    data class ProfileInfoContacts(
        val label: String,
        val value: String
    ): ProfileInfo()

    data class ProfileInfoPost(
        val title: String,
        val body: String,
        val userId: Int?,
        val id: Int?,
        val userFullName: String?,
        val countComments: Int?
    ): ProfileInfo()

    enum class ProfileInfoType{
        ProfileInfoContacts,
        ProfileInfoPost,
    }
}