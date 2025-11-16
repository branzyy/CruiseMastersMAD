package com.example.cruisemastersmad.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruisemastersmad.databinding.ActivityProfileBinding
import com.example.cruisemastersmad.ui.adapters.BookingAdapter
import com.example.cruisemastersmad.ui.adapters.PurchaseAdapter
import ui.models.Booking
import ui.models.Purchase

class ProfileActivity : AppCompatActivity() {

    private lateinit var binding: ActivityProfileBinding
    private lateinit var purchaseAdapter: PurchaseAdapter
    private lateinit var bookingAdapter: BookingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupAdapters()
        loadUserData()
        loadPurchases()
        loadBookings()
        setupToolbar()
    }

    private fun setupAdapters() {
        purchaseAdapter = PurchaseAdapter()
        bookingAdapter = BookingAdapter(emptyList())

        binding.recyclerViewPurchases.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = purchaseAdapter
        }

        binding.recyclerViewBookings.apply {
            layoutManager = LinearLayoutManager(this@ProfileActivity)
            adapter = bookingAdapter
        }
    }

    private fun loadUserData() {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userName = sharedPref.getString("user_name", "User")
        val userEmail = sharedPref.getString("user_email", "user@example.com")

        binding.tvUserName.text = userName
        binding.tvUserEmail.text = userEmail
    }

    private fun loadPurchases() {
        val sharedPref = getSharedPreferences("user_purchases", MODE_PRIVATE)
        val purchasesSet = sharedPref.getStringSet("purchases", setOf()) ?: setOf()

        val purchases = purchasesSet.map { purchaseData ->
            val parts = purchaseData.split("|")
            Purchase(
                id = parts[0].toInt(),
                carName = parts[1],
                price = parts[2].toDouble(),
                purchaseDate = parts[3],
                status = parts[4]
            )
        }.reversed()

        // Use submitList for PurchaseAdapter (ListAdapter)
        purchaseAdapter.submitList(purchases)

        if (purchases.isEmpty()) {
            binding.tvNoPurchases.visibility = android.view.View.VISIBLE
            binding.recyclerViewPurchases.visibility = android.view.View.GONE
        } else {
            binding.tvNoPurchases.visibility = android.view.View.GONE
            binding.recyclerViewPurchases.visibility = android.view.View.VISIBLE
        }
    }

    private fun loadBookings() {
        val sharedPref = getSharedPreferences("user_bookings", MODE_PRIVATE)
        val bookingsSet = sharedPref.getStringSet("bookings", setOf()) ?: setOf()

        val bookings = if (bookingsSet.isNotEmpty()) {
            bookingsSet.map { bookingData ->
                val parts = bookingData.split("|")
                Booking(
                    id = parts[0].toInt(),
                    userId = parts[1].toInt(),
                    carName = parts[2],
                    startDate = parts[3],
                    endDate = parts[4]
                )
            }.reversed()
        } else {
            // Sample data if no bookings exist
            listOf(
                Booking(1, 1, "BMW M8", "2024-01-15", "2024-01-20"),
                Booking(2, 1, "Mercedes AMG", "2024-02-01", "2024-02-05")
            )
        }

        // For BookingAdapter (RecyclerView.Adapter), create new instance with data
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

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }
    }

    // Helper method to add sample purchases for testing
    private fun addSamplePurchases() {
        val sharedPref = getSharedPreferences("user_purchases", MODE_PRIVATE)
        val samplePurchases = setOf(
            "1|BMW M8|1500.0|2024-01-15|Completed",
            "2|Mercedes AMG|1200.0|2024-02-01|Processing"
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
            "1|1|BMW M8|2024-01-15|2024-01-20",
            "2|1|Mercedes AMG|2024-02-01|2024-02-05"
        )

        with(sharedPref.edit()) {
            putStringSet("bookings", sampleBookings)
            apply()
        }
    }
}