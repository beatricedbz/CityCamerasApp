package com.example.citycamerasapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.citycamerasapp.databinding.FragmentPreviewCameraBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PreviewCameraFragment : Fragment(R.layout.fragment_preview_camera) {

    private var _binding: FragmentPreviewCameraBinding? = null
    lateinit var adapter: CameraAdapter
    private val binding get() = _binding!!
    private val viewModel: CameraViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        _binding = FragmentPreviewCameraBinding.bind(view)
        adapter = CameraAdapter{clickListener ->
            var bundle: Bundle? = null
            bundle?.putInt("id", clickListener.id)

            parentFragmentManager
            .beginTransaction()
            .replace(R.id.flFragmentHolder, VideoCameraFragment.newInstance())
            .commit()
        }

        binding.apply{
            rvCameras.layoutManager = GridLayoutManager(context, 1)
            rvCameras.adapter = adapter
        }
        observeCameras()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = PreviewCameraFragment()
    }
    private fun observeCameras() {
        with(viewModel) {
            getAllCameras()
            cameraList.observe(viewLifecycleOwner) {
                adapter.fillCameras(it)
            }
        }
    }
}