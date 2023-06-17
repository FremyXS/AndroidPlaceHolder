package com.example.androidplaceholder.data.requests

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("id")
    val id: Int?,
    @SerializedName("name")
    val name: String?,
    @SerializedName("username")
    val username: String?,
    @SerializedName("email")
    val email: String?,
)