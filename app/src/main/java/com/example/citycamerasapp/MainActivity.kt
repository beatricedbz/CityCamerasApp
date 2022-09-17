package com.example.citycamerasapp

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.citycamerasapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    lateinit var adapter: CameraAdapter
    private val viewModel: CameraViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CameraAdapter()
        binding.rvCameras.adapter = adapter
        observeCameras()
    }

    private fun observeCameras() {
        with(viewModel) {
            getAllCameras()
            cameraList.observe(this@MainActivity) {
                adapter.fillCameras(it)
            }
        }
    }

    private fun initRecycler() {

    }
}