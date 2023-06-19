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

    data class ProfileInfoAlbum(
        val userId: Int?,
        val id: Int?,
        val title: String?,
        val img: String?,
        val userFullName: String?,
        val countPhotos: Int?
    ): ProfileInfo()

    enum class ProfileInfoType{
        ProfileInfoContacts,
        ProfileInfoPost,
        ProfileInfoAlbum,
    }
}