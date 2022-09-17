package com.example.citycamerasapp

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repository: CameraRepository
    ) : ViewModel() {
    val cameraList = MutableLiveData<List<Camera>>()
    val errorMessage = MutableLiveData<String>()

    fun getAllCameras() {
        val response = repository.getAllCameras()
        response.enqueue(object : Callback<List<Camera>> {
            override fun onResponse(call: Call<List<Camera>>, response: Response<List<Camera>>) {
                cameraList.postValue(response.body())
            }

            override fun onFailure(call: Call<List<Camera>>, t: Throwable) {
                errorMessage.postValue(t.message)
            }
        })
    }
}