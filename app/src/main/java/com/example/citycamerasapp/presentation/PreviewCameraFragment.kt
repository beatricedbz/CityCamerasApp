package com.example.citycamerasapp.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.example.citycamerasapp.R
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
        adapter = CameraAdapter { clickListener ->
            val bundle = Bundle()
            bundle.putInt("id", clickListener.id)
            val videoFragment = VideoCameraFragment()
            videoFragment.arguments = bundle
            parentFragmentManager
                .beginTransaction()
                .addToBackStack("name")
                .replace(R.id.flFragmentHolder, videoFragment)
                .commit()
        }
        binding.apply {
            rvCameras.layoutManager = GridLayoutManager(context, 1)
            rvCameras.adapter = adapter
            srlRoot.setOnRefreshListener {
                viewModel.getAllCameras()
            }
        }
        observeIsRefreshing()
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
                adapter.submitList(it)
            }
        }
    }

    private fun observeIsRefreshing() {
        viewModel.isRefreshing.observe(viewLifecycleOwner) {
            binding.srlRoot.isRefreshing = it
        }
    }
}