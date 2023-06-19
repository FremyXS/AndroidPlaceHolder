package com.example.androidplaceholder.di

import com.example.androidplaceholder.di.modules.NetworkModule
import com.example.androidplaceholder.di.modules.RepositoryModule
import com.example.androidplaceholder.di.modules.UseCaseModule
import com.example.androidplaceholder.presentation.MainActivity
import com.example.androidplaceholder.presentation.fragments.AlbumsContainerFragment
import com.example.androidplaceholder.presentation.fragments.PhotosContainerFragment
import com.example.androidplaceholder.presentation.fragments.PostOpenFragment
import com.example.androidplaceholder.presentation.fragments.PostsContainerFragment
import com.example.androidplaceholder.presentation.fragments.UserProfileFragment
import com.example.androidplaceholder.presentation.fragments.UsersContainerFragment
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [NetworkModule::class, RepositoryModule::class, UseCaseModule:: class])
interface AppComponent {
    fun inject(activity: PostsContainerFragment)
    fun inject(activity: PostOpenFragment)
    fun inject(activity: AlbumsContainerFragment)
    fun inject(activity: PhotosContainerFragment)
    fun inject(activity: UserProfileFragment)
    fun inject(activity: UsersContainerFragment)
}