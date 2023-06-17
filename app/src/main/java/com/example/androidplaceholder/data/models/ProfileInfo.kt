package com.example.androidplaceholder.data.models

sealed class ProfileInfo (){
    data class ProfileInfoContacts(
        val label: String,
        val value: String
    ): ProfileInfo()
}