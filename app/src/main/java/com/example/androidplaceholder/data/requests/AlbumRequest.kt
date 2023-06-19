package com.example.androidplaceholder.data.requests

import com.google.gson.annotations.SerializedName

data class AlbumRequest (
    @SerializedName("userId")
    val userId: Int?,
    @SerializedName("id")
    val id: Int?,
    @SerializedName("title")
    val title: String
)