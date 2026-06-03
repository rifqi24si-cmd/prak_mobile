package com.example.kayemob.Message

import android.content.Intent
import android.os.Bundle
import android.view.*
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kayemob.Message.tutorial.TutorialMessageActivity
import com.example.kayemob.R
import com.example.kayemob.databinding.FragmentMessageBinding

class MessageFragment : Fragment() {
    private var _binding: FragmentMessageBinding? = null
    private val binding get() = _binding!!

    // Data dummy untuk list pesan
    private val messageList = listOf(
        MessageModel("Andi", "Halo, apa kabar?", "https://picsum.photos/seed/user1/100"),
        MessageModel("Bella", "Jangan lupa kirim tugas ya", "https://picsum.photos/seed/user2/100"),
        MessageModel("Chandra", "Besok ada rapat jam 10", "https://picsum.photos/seed/user3/100")
    )

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMessageBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Setup Toolbar
        (requireActivity() as AppCompatActivity).setSupportActionBar(binding.toolbar)
        (requireActivity() as AppCompatActivity).supportActionBar?.apply {
            title = "Message"
        }
        setHasOptionsMenu(true)

        // Setup ListView dengan MessageAdapter
        val adapter = MessageAdapter(requireContext(), R.layout.item_message, messageList)
        binding.listViewMessages.adapter = adapter
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
