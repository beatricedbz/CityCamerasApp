package com.example.citycamerasapp

import android.os.Bundle
import android.view.View
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.example.citycamerasapp.databinding.FragmentVideoCameraBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class VideoCameraFragment : Fragment(R.layout.fragment_video_camera) {

    private var _binding: FragmentVideoCameraBinding? = null
    private val binding get() = _binding!!

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        _binding = FragmentVideoCameraBinding.bind(view)
        binding.bBack.setOnClickListener{
            parentFragmentManager.popBackStack()
        }
        val args = this.arguments
        val inputData = args?.getInt("id")
        binding.wvCameraVideo.apply{
            webViewClient = WebViewClient()
            settings.javaScriptEnabled = true
            loadUrl("http://krkvideo14.orionnet.online/cam${inputData}/embed.html")
            settings.setSupportZoom(true)

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        @JvmStatic
        fun newInstance() = VideoCameraFragment()
    }
}