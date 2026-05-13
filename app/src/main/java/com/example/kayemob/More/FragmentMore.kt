package com.example.kayemob.More

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.kayemob.R
import com.example.kayemob.databinding.FragmentMoreBinding

class FragmentMore : Fragment() {
    private var _binding: FragmentMoreBinding? = null
    private val binding get() = _binding!!

    // Data baru untuk CustomAdapter
    private val messageDataList = listOf(
        MessageItem("Alya", "Halo! Apa kabar?", "https://ui-avatars.com/api/?name=Alya&background=random"),
        MessageItem("Budi", "Sudah makan?", "https://ui-avatars.com/api/?name=Budi&background=random"),
        MessageItem("Citra", "Jangan lupa tugasnya ya!", "https://ui-avatars.com/api/?name=Citra&background=random"),
        MessageItem("Dika", "Besok kita rapat jam 9", "https://ui-avatars.com/api/?name=Dika&background=random"),
        MessageItem("Eka", "Nice job kemarin!", "https://ui-avatars.com/api/?name=Eka&background=random")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMoreBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        // Inisialisasi CustomAdapter
        val adapter = CustomAdapter(
            requireContext(),
            R.layout.item_custom,
            messageDataList
        )
        
        // Hubungkan ListView dengan CustomAdapter
        binding.listViewItems.adapter = adapter
        
        // Aksi klik item
        binding.listViewItems.setOnItemClickListener { _, _, position, _ ->
            val selectedItem = messageDataList[position]
            Toast.makeText(
                requireContext(), 
                "Pesan dari: ${selectedItem.title}", 
                Toast.LENGTH_SHORT
            ).show()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}