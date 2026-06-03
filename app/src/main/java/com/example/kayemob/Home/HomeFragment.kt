package com.example.kayemob.Home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.kayemob.Data.Api.CatFactApiClient
import com.example.kayemob.Data.Api.PhotoApiClient
import com.example.kayemob.Home.photo.PhotoAdapter
import com.example.kayemob.databinding.FragmentHomeBinding
import com.example.kayemob.pertemuan_2.SecondActivity
import com.example.kayemob.pertemuan_3.ThirdActivity
import com.example.kayemob.pertemuan_4.MainActivity as P4MainActivity
import com.example.kayemob.pertemuan_6.SixthActivity
import com.example.kayemob.pertemuan_10.TenthActivity
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupNavigation()
        setupCatFact()
        setupPhotoGallery()

        binding.btnRefresh.setOnClickListener {
            setupCatFact()
        }
    }

    private fun setupNavigation() {
        binding.btnP2.setOnClickListener {
            startActivity(Intent(requireContext(), SecondActivity::class.java))
        }
        binding.btnP3.setOnClickListener {
            startActivity(Intent(requireContext(), ThirdActivity::class.java))
        }
        binding.btnP4.setOnClickListener {
            startActivity(Intent(requireContext(), P4MainActivity::class.java))
        }
        binding.btnP5.setOnClickListener {
            // Asumsi Pertemuan 5 menggunakan Activity yang sama atau sesuaikan jika ada activity spesifik
            Toast.makeText(requireContext(), "Halaman Pertemuan 5 belum ditentukan", Toast.LENGTH_SHORT).show()
        }
        binding.btnP6.setOnClickListener {
            startActivity(Intent(requireContext(), SixthActivity::class.java))
        }
        binding.btnSensor.setOnClickListener {
            startActivity(Intent(requireContext(), TenthActivity::class.java))
        }
    }

    private fun setupCatFact() {
        lifecycleScope.launch {
            try {
                val response = CatFactApiClient.apiService.getCatFact()
                binding.tvCatFact.text = "\"${response.fact}\""
            } catch (e: Exception) {
                binding.tvCatFact.text = "Gagal memuat fakta kucing."
            }
        }
    }

    private fun setupPhotoGallery() {
        binding.rvGallery.layoutManager = LinearLayoutManager(requireContext())
        lifecycleScope.launch {
            try {
                val photos = PhotoApiClient.apiService.getPhotos()
                binding.rvGallery.adapter = PhotoAdapter(photos)
            } catch (e: Exception) {
                Toast.makeText(requireContext(), "Gagal memuat foto", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
