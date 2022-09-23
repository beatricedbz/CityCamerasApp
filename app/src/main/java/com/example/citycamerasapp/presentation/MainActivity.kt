package com.example.citycamerasapp.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.citycamerasapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        supportFragmentManager
            .beginTransaction()
            .replace(binding.flFragmentHolder.id, PreviewCameraFragment.newInstance())
            .commit()
    }

}