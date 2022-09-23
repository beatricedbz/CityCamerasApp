package com.example.citycamerasapp.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.citycamerasapp.data.Camera
import com.example.citycamerasapp.data.CameraRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class CameraViewModel @Inject constructor(
    private val repository: CameraRepository,
) : ViewModel() {
    val cameraList = MutableLiveData<List<Camera>>()
    val errorMessage = MutableLiveData<String>()
    val isRefreshing = MutableLiveData<Boolean>(false)

    fun getAllCameras() {
        isRefreshing.value = true
        val response = repository.getAllCameras()
        response.enqueue(object : Callback<List<Camera>> {
            override fun onResponse(call: Call<List<Camera>>, response: Response<List<Camera>>) {
                cameraList.postValue(response.body())
                isRefreshing.value = false
            }

            override fun onFailure(call: Call<List<Camera>>, t: Throwable) {
                errorMessage.postValue(t.message)
                isRefreshing.value = false
            }
        })
    }
}