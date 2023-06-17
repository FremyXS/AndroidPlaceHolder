package com.example.androidplaceholder.di

import com.example.androidplaceholder.di.modules.NetworkModule
import com.example.androidplaceholder.di.modules.RepositoryModule
import com.example.androidplaceholder.di.modules.UseCaseModule
import com.example.androidplaceholder.presentation.MainActivity
import com.example.androidplaceholder.presentation.fragments.PostOpenFragment
import com.example.androidplaceholder.presentation.fragments.PostsContainerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule:: class])
interface AppComponent {
    fun inject(activity: PostsContainerFragment)
}