package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.AlbumDefault
import com.example.androidplaceholder.domain.usecases.IGetAlbumsUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AlbumViewModel @Inject constructor(private val useCase: IGetAlbumsUseCase) : ViewModel() {

    private val postListLiveData: MutableLiveData<List<AlbumDefault.AlbumInfo>> =
        MutableLiveData<List<AlbumDefault.AlbumInfo>>()

    fun setList() {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke()
            }
            postListLiveData.postValue(result)
        }
    }

    fun getList(): MutableLiveData<List<AlbumDefault.AlbumInfo>> {
        return postListLiveData
    }
}