package com.example.androidplaceholder.di.modules

import com.example.androidplaceholder.data.models.Comment
import com.example.androidplaceholder.data.models.PostDefault
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.data.remote.RetrofitService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
class NetworkModule {
    private val retrofit = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .client(OkHttpClient())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val retrofitService = retrofit.create(RetrofitService::class.java)
    @Provides
    fun getApi() : RetrofitService=
        retrofitService
}