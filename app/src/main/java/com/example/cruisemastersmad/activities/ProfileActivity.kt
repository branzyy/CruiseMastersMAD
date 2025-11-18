package com.example.cruisemastersmad.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruisemastersmad.databinding.ActivityProfileBinding
import com.example.cruisemastersmad.ui.adapters.BookingAdapter
import com.example.cruisemastersmad.ui.adapters.PurchaseAdapter
import com.example.cruisemastersmad.models.Booking
import com.example.cruisemastersmad.models.Purchase
import com.example.cruisemastersmad.ui.viewmodels.BookingViewModel
import com.example.cruisemastersmad.ui.viewmodels.PurchaseViewModel

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var purchaseAdapter: PurchaseAdapter
    private lateinit var bookingAdapter: BookingAdapter
    private lateinit var purchaseViewModel: PurchaseViewModel
    private lateinit var bookingViewModel: BookingViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        purchaseViewModel = ViewModelProvider(this).get(PurchaseViewModel::class.java)
        bookingViewModel = ViewModelProvider(this).get(BookingViewModel::class.java)

        setupAdapters()
        setupObservers()
        loadUserData()
        setupToolbar()
        setupLogoutButton()
    }

    private fun setupAdapters() {
        purchaseAdapter = PurchaseAdapter()
        bookingAdapter = BookingAdapter(emptyList())

        binding.recyclerViewPurchases.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = purchaseAdapter
            isNestedScrollingEnabled = true
        }

        binding.recyclerViewBookings.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = bookingAdapter
            isNestedScrollingEnabled = true
        }
    }

    private fun setupObservers() {
        purchaseViewModel.purchases.observe(this) { purchases ->
            purchaseAdapter.submitList(purchases)
            if (purchases.isEmpty()) {
                binding.tvNoPurchases.visibility = android.view.View.VISIBLE
                binding.recyclerViewPurchases.visibility = android.view.View.GONE
            } else {
                binding.tvNoPurchases.visibility = android.view.View.GONE
                binding.recyclerViewPurchases.visibility = android.view.View.VISIBLE
            }
        }

        bookingViewModel.bookings.observe(this) { bookings ->
            bookingAdapter = BookingAdapter(bookings)
            binding.recyclerViewBookings.adapter = bookingAdapter
            if (bookings.isEmpty()) {
                binding.tvNoBookings.visibility = android.view.View.VISIBLE
                binding.recyclerViewBookings.visibility = android.view.View.GONE
            } else {
                binding.tvNoBookings.visibility = android.view.View.GONE
                binding.recyclerViewBookings.visibility = android.view.View.VISIBLE
            }
        }
    }

    private fun loadUserData() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", "User")
        val userEmail = sharedPref.getString("user_email", "user@example.com")
        val userId = sharedPref.getLong("user_id", -1)

        binding.tvUserName.text = userName ?: "User"
        binding.tvUserEmail.text = userEmail ?: "user@example.com"

        // Load purchases and bookings from database using user email
        purchaseViewModel.loadPurchases(userEmail ?: "")
        bookingViewModel.loadBookings(userEmail ?: "")
    }



    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    private fun setupLogoutButton() {
        binding.btnLogout.setOnClickListener {
            logoutUser()
        }
    }

    private fun logoutUser() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        with(sharedPref.edit()) {
            clear()
            apply()
        }

        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    // Helper method to add sample purchases for testing
    private fun addSamplePurchases() {
        val sharedPref = getSharedPreferences("user_purchases", MODE_PRIVATE)
        val samplePurchases = setOf(
            "P001|BMW M8|2024-01-15|user@example.com|Completed",
            "P002|Mercedes AMG|2024-02-01|user@example.com|Processing"
        )

        with(sharedPref.edit()) {
            putStringSet("purchases", samplePurchases)
            apply()
        }
    }

    // Helper method to add sample bookings for testing
    private fun addSampleBookings() {
        val sharedPref = getSharedPreferences("user_bookings", MODE_PRIVATE)
        val sampleBookings = setOf(
            "B001|BMW M8|2024-01-15|2024-01-20|user@example.com|Confirmed",
            "B002|Mercedes AMG|2024-02-01|2024-02-05|user@example.com|Pending"
        )

        with(sharedPref.edit()) {
            putStringSet("bookings", sampleBookings)
            apply()
        }
    }
}