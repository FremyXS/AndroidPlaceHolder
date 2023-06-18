package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androidplaceholder.data.models.ProfileInfo

class ProfileViewModel: ViewModel() {
    private val currentInfo = MutableLiveData<ProfileInfo.ProfileInfoType>(ProfileInfo.ProfileInfoType.ProfileInfoContacts)
    private val contactsInfo = MutableLiveData<List<ProfileInfo.ProfileInfoContacts>>()
    private val postsInfo = MutableLiveData<List<ProfileInfo.ProfileInfoPost>>()

    fun setCurrentContacts(){
        currentInfo.value = ProfileInfo.ProfileInfoType.ProfileInfoContacts
    }

    fun setCurrentPosts(){
        currentInfo.value = ProfileInfo.ProfileInfoType.ProfileInfoPost
    }

    fun getCurrent(): MutableLiveData<ProfileInfo.ProfileInfoType> {
        return currentInfo
    }
    fun getList(): MutableLiveData<out List<ProfileInfo>> {
        return when(currentInfo.value){
            ProfileInfo.ProfileInfoType.ProfileInfoPost -> postsInfo
            ProfileInfo.ProfileInfoType.ProfileInfoContacts -> contactsInfo
            else -> contactsInfo
        }
    }

    fun init(){
        val contacts = mutableListOf<ProfileInfo.ProfileInfoContacts>()
        val temps = listOf("email", "phone", "website")
        val temps2 = listOf("hot@mail.com", "+7(999)-555-35-35", "poshel-nahuy")

        for (i in 0..2){
            contacts.add(ProfileInfo.ProfileInfoContacts(
                temps[i],
                temps2[i]
            ))
        }

        contactsInfo.value = contacts

        val posts = mutableListOf<ProfileInfo.ProfileInfoPost>()
        val tempsPost = listOf("Title", "Title2", "Title3")
        val temps2Post = listOf("BodyBodyBodyBodyBodyBody", "BodyBodyBodyBodyBodyBodyBody", "Body")

        for (i in 0..10){
            posts.add(ProfileInfo.ProfileInfoPost(
                tempsPost[i % 3],
                temps2Post[i % 3]
            ))
        }

        postsInfo.value = posts
    }
}