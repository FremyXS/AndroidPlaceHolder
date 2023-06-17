package com.example.androidplaceholder.data.models

sealed class ProfileInfo (){
    data class ProfileInfoContacts(
        val label: String,
        val value: String
    ): ProfileInfo()

    data class ProfileInfoPost(
        val title: String,
        val body: String
    ): ProfileInfo()

    enum class ProfileInfoType{
        ProfileInfoContacts,
        ProfileInfoPost,
    }
}