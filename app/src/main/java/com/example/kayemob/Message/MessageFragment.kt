package com.example.kayemob.Message

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import com.example.kayemob.Data.AppDatabase
import com.example.kayemob.Data.Entity.MessageEntity
import com.example.kayemob.Message.tutorial.TutorialMessageActivity
import com.example.kayemob.R
import com.example.kayemob.databinding.FragmentMessageBinding
import kotlinx.coroutines.launch

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!
    
    private lateinit var db: AppDatabase
    private val messages = mutableListOf<MessageModel>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        db = AppDatabase.getInstance(requireContext())

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Zeus Messages"
        }
        setHasOptionsMenu(true)

        loadMessages()
    }

    private fun loadMessages() {
        lifecycleScope.launch {
            var data = db.messageDao().getAll()
            
            // Jika database kosong, kita isi dengan data awal (Pre-populate)
            if (data.isEmpty()) {
                val initialMessages = listOf(
                    MessageEntity(sender = "Official Zeus", content = "Selamat datang di Zeus Gym! Siap latihan hari ini?", avatarUrl = "https://picsum.photos/seed/zeus/100"),
                    MessageEntity(sender = "Coach Alex", content = "Jangan lupa jadwal Personal Training jam 4 sore.", avatarUrl = "https://picsum.photos/seed/coach/100"),
                    MessageEntity(sender = "Admin", content = "Membership Anda akan berakhir dalam 3 hari.", avatarUrl = "https://picsum.photos/seed/admin/100")
                )
                initialMessages.forEach { db.messageDao().insert(it) }
                data = db.messageDao().getAll()
            }

            // Konversi dari MessageEntity (Room) ke MessageModel (Adapter)
            messages.clear()
            data.forEach { 
                messages.add(MessageModel(it.sender, it.content, it.avatarUrl))
            }

            // Setup ListView dengan MessageAdapter
            val adapter = MessageAdapter(requireContext(), R.layout.item_message, messages)
            binding.listViewMessages.adapter = adapter
        }
    }

    @Deprecated("Deprecated in Java")
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.message_toolbar_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    @Deprecated("Deprecated in Java")
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_tutorial -> {
                val intent = Intent(requireContext(), TutorialMessageActivity::class.java)
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
