package com.example.kayemob.zeusgym

import android.Manifest
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import com.example.kayemob.databinding.FragmentZeusHomeBinding
import com.example.kayemob.utils.PermissionHelper
import com.example.kayemob.utils.ReminderHelper
import java.util.Calendar

class ZeusHomeFragment : Fragment() {
    private var _binding: FragmentZeusHomeBinding? = null
    private val binding get() = _binding!!

    private val notificationPermissionLauncher =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                Toast.makeText(requireContext(), "Notifikasi diizinkan", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Notifikasi ditolak", Toast.LENGTH_SHORT).show()
            }
        }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentZeusHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Request permission if needed
        if (PermissionHelper.isNotificationPermissionRequired()) {
            val permission = Manifest.permission.POST_NOTIFICATIONS
            if (!PermissionHelper.hasPermission(requireContext(), permission)) {
                PermissionHelper.requestPermission(
                    notificationPermissionLauncher,
                    permission
                )
            }
        }

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

        binding.btnSetReminder.setOnClickListener {
            val minutesStr = binding.etReminderMinutes.text.toString()
            if (minutesStr.isNotEmpty()) {
                val minutes = minutesStr.toInt()
                val calendar = Calendar.getInstance().apply {
                    add(Calendar.MINUTE, minutes)
                }

                ReminderHelper.setReminder(
                    context = requireContext(),
                    hour = calendar.get(Calendar.HOUR_OF_DAY),
                    minute = calendar.get(Calendar.MINUTE),
                    title = "Zeus Gym Reminder",
                    message = "Waktunya latihan! Jangan kasih kendor!",
                    targetActivity = ZeusGymActivity::class.java
                )

                Toast.makeText(requireContext(), "Reminder diset untuk $minutes menit lagi", Toast.LENGTH_SHORT).show()
                binding.etReminderMinutes.text.clear()
            } else {
                Toast.makeText(requireContext(), "Masukkan jumlah menit", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
