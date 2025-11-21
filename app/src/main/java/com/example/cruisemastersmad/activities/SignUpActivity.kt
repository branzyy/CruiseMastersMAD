package com.example.cruisemastersmad.activities

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.example.cruisemastersmad.databinding.ActivitySignupBinding
import com.example.cruisemastersmad.activities.HomeActivity
import com.example.cruisemastersmad.models.User
import com.example.cruisemastersmad.viewmodels.LoginViewModel

class SignUpActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySignupBinding
    private lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySignupBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loginViewModel = ViewModelProvider(this)[LoginViewModel::class.java]

        setupViews()
    }

    private fun setupViews() {
        binding.btnSignUp.setOnClickListener {
            val firstName = binding.etFirstName.text.toString()
            val lastName = binding.etLastName.text.toString()
            val email = binding.etEmail.text.toString()
            val password = binding.etPassword.text.toString()
            val confirmPassword = binding.etConfirmPassword.text.toString()

            if (firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty() &&
                password.isNotEmpty() && confirmPassword.isNotEmpty()) {

                if (password == confirmPassword) {
                    if (password.length >= 6) {
                        checkEmailAndSignUp(firstName, lastName, email, password)
                    } else {
                        Toast.makeText(this, "Password must be at least 6 characters", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this, "Passwords don't match", Toast.LENGTH_SHORT).show()
                }
            } else {
                Toast.makeText(this, "Please fill all fields", Toast.LENGTH_SHORT).show()
            }
        }

        binding.tvLogin.setOnClickListener {
            startActivity(Intent(this, com.example.cruisemastersmad.activities.LoginActivity::class.java))
        }

        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }

    private fun checkEmailAndSignUp(firstName: String, lastName: String, email: String, password: String) {
        binding.progressBar.visibility = android.view.View.VISIBLE
        binding.btnSignUp.isEnabled = false

        loginViewModel.checkEmailExists(email) { exists ->
            runOnUiThread {
                if (exists) {
                    Toast.makeText(this, "Email already exists", Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = android.view.View.GONE
                    binding.btnSignUp.isEnabled = true
                } else {
                    signUpUser(firstName, lastName, email, password)
                }
            }
        }
    }

    private fun signUpUser(firstName: String, lastName: String, email: String, password: String) {
        val user = User(
            id = 0,
            firstname = firstName,
            lastname = lastName,
            email = email,
            password = password
        )

        loginViewModel.signup(user) { userId ->
            runOnUiThread {
                // Store user data in SharedPreferences
                val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
                with(sharedPref.edit()) {
                    putLong("user_id", userId)
                    putString("user_email", email)
                    putString("user_name", "$firstName $lastName")
                    putBoolean("is_logged_in", true)
                    apply()
                }

                Toast.makeText(this, "Account created successfully!", Toast.LENGTH_SHORT).show()
                startActivity(Intent(this, HomeActivity::class.java))
                finish()

                binding.progressBar.visibility = android.view.View.GONE
                binding.btnSignUp.isEnabled = true
            }
        }
    }
}
