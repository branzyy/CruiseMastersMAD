package com.example.cruisemastersmad.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cruisemastersmad.databinding.ActivityHomeBinding

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupViews()
        loadUserData()
    }

    private fun setupViews() {
        binding.btnBrowseModels.setOnClickListener {
            startActivity(Intent(this, ModelsActivity::class.java))
        }

        binding.btnProfile.setOnClickListener {
            startActivity(Intent(this, ProfileActivity::class.java))
        }

        binding.btnLogout.setOnClickListener {
            logoutUser()
        }
    }

    private fun loadUserData() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", "User")
        val userEmail = sharedPref.getString("user_email", "")

        binding.tvWelcome.text = "Welcome, $userName!"
        // You can also display email if needed
        // binding.tvUserEmail.text = userEmail
    }

    private fun logoutUser() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            clear()
            apply()
        }

        startActivity(Intent(this, com.example.cruisemastersmad.ui.activities.MainActivity::class.java))
        finish()
    }
}