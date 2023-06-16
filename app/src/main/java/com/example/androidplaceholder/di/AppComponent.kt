package com.example.androidplaceholder.di

import com.example.androidplaceholder.di.modules.NetworkModule
import com.example.androidplaceholder.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class])
interface AppComponent {
    fun inject(activity: MainActivity)
}