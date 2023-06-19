package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.ProfileInfo
import com.example.androidplaceholder.domain.usecases.interfaces.IGetAlbumsByUserIdUseCase
import com.example.androidplaceholder.domain.usecases.interfaces.IGetPostsByUserIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ProfileViewModel
    @Inject constructor(
        private val getPostUseCase: IGetPostsByUserIdUseCase,
        private val  getAlbumsByUserIdUseCase: IGetAlbumsByUserIdUseCase
    ): ViewModel() {
    private val currentInfo = MutableLiveData<ProfileInfo.ProfileInfoType>(ProfileInfo.ProfileInfoType.ProfileInfoContacts)
    private val contactsInfo = MutableLiveData<List<ProfileInfo.ProfileInfoContacts>>()
    private val postsInfo = MutableLiveData<List<ProfileInfo.ProfileInfoPost>>()
    private val albumsInfo = MutableLiveData<List<ProfileInfo.ProfileInfoAlbum>>()

    fun setCurrentContacts(){
        currentInfo.value = ProfileInfo.ProfileInfoType.ProfileInfoContacts
    }

    fun setCurrentPosts(){
        currentInfo.value = ProfileInfo.ProfileInfoType.ProfileInfoPost
    }

    fun setCurrentAlbums(){
        currentInfo.value = ProfileInfo.ProfileInfoType.ProfileInfoAlbum
    }

    fun getCurrent(): MutableLiveData<ProfileInfo.ProfileInfoType> {
        return currentInfo
    }
    fun getList(): MutableLiveData<out List<ProfileInfo>> {
        return when(currentInfo.value){
            ProfileInfo.ProfileInfoType.ProfileInfoPost -> postsInfo
            ProfileInfo.ProfileInfoType.ProfileInfoContacts -> contactsInfo
            ProfileInfo.ProfileInfoType.ProfileInfoAlbum -> albumsInfo
            else -> contactsInfo
        }
    }

    fun init(userId: Int, email: String, phone: String, web: String){
        val contacts = mutableListOf<ProfileInfo.ProfileInfoContacts>()
        val temps = listOf("email", "phone", "website")
        val temps2 = listOf(email, phone, web)

        for (i in 0..2){
            contacts.add(ProfileInfo.ProfileInfoContacts(
                temps[i],
                temps2[i]
            ))
        }

        contactsInfo.value = contacts

        viewModelScope.launch {
            val resultPosts = withContext(Dispatchers.IO) {
                getPostUseCase.invoke(userId)
            }
            postsInfo.postValue(resultPosts)

            val resultAlbums = withContext(Dispatchers.IO) {
                getAlbumsByUserIdUseCase.invoke(userId)
            }
            albumsInfo.postValue(resultAlbums)
        }
    }
}