package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.UserDefault
import com.example.androidplaceholder.domain.usecases.IGetUsersUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class UsersContainerViewModel
    @Inject constructor(
        private val getUsersUseCase: IGetUsersUseCase
    ): ViewModel()
{
    private val usersLiveData = MutableLiveData<List<UserDefault.UserInfo>>()

    fun getUsers() = usersLiveData

    fun init(){
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                getUsersUseCase.invoke()
            }
            usersLiveData.postValue(result)
        }
    }
}