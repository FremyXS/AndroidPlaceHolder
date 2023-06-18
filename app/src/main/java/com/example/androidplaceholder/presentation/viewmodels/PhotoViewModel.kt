package com.example.androidplaceholder.presentation.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androidplaceholder.data.models.Photo
import com.example.androidplaceholder.domain.usecases.IGetPhotosByAlbumIdUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class PhotoViewModel @Inject constructor(private val useCase: IGetPhotosByAlbumIdUseCase) :
    ViewModel() {

    private val photosListLiveData = MutableLiveData<List<Photo.PhotoBigger>>()
    
    fun setList(albumId: Int) {
        viewModelScope.launch {
            val result = withContext(Dispatchers.IO) {
                useCase.invoke(albumId)
            }
            photosListLiveData.postValue(result)
        }
    }

    fun getList(): MutableLiveData<List<Photo.PhotoBigger>> {
        return photosListLiveData
    }
}