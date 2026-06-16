package com.example.kayemob.zeusgym

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kayemob.databinding.FragmentZeusHomeBinding

class ZeusHomeFragment : Fragment() {
    private var _binding: FragmentZeusHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZeusHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.btnWorkout.setOnClickListener {
            Toast.makeText(context, "Membuka Program Latihan...", Toast.LENGTH_SHORT).show()
        }
        binding.btnTrainer.setOnClickListener {
            Toast.makeText(context, "Mencari Trainer Tersedia...", Toast.LENGTH_SHORT).show()
        }
        binding.btnMembership.setOnClickListener {
            Toast.makeText(context, "Status: Member Gold", Toast.LENGTH_SHORT).show()
        }
        binding.btnLogout.setOnClickListener {
            activity?.finish()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
