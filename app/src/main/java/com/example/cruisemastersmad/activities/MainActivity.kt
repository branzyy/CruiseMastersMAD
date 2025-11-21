package com.example.cruisemastersmad.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cruisemastersmad.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Check if user is already logged in
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val isLoggedIn = sharedPref.getBoolean("is_logged_in", false)

        if (isLoggedIn) {
            startActivity(Intent(this, com.example.cruisemastersmad.activities.HomeActivity::class.java))
            finish()
        } else {
            setupClickListeners()
        }
    }

    private fun setupClickListeners() {
        binding.btnLogin.setOnClickListener {
            startActivity(Intent(this, com.example.cruisemastersmad.activities.LoginActivity::class.java))
        }

        binding.btnSignUp.setOnClickListener {
            startActivity(Intent(this, com.example.cruisemastersmad.activities.SignUpActivity::class.java))
        }
    }
}
