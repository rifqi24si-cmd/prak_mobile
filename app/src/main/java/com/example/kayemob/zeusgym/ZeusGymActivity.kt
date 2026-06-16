package com.example.kayemob.zeusgym

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.kayemob.R
import com.example.kayemob.databinding.ActivityZeusGymBinding

class ZeusGymActivity : AppCompatActivity() {
    private lateinit var binding: ActivityZeusGymBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityZeusGymBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Default Fragment
        loadFragment(ZeusHomeFragment())

        binding.bottomNavigation.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.nav_home -> {
                    loadFragment(ZeusHomeFragment())
                    true
                }
                R.id.nav_about -> {
                    loadFragment(ZeusAboutFragment())
                    true
                }
                R.id.nav_message -> {
                    loadFragment(com.example.kayemob.Message.MessageFragment())
                    true
                }
                R.id.nav_note -> {
                    loadFragment(com.example.kayemob.Note.NoteFragment())
                    true
                }
                R.id.nav_profile -> {
                    loadFragment(ZeusProfileFragment())
                    true
                }
                else -> false
            }
        }
    }

    private fun loadFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }
}
