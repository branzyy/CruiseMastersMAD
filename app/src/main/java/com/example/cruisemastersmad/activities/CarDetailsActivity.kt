package com.example.cruisemastersmad.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cruisemastersmad.R
import com.example.cruisemastersmad.databinding.ActivityCarDetailsBinding
import com.example.cruisemastersmad.models.Car
import com.example.cruisemastersmad.models.Purchase
import com.example.cruisemastersmad.models.Booking

class CarDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarDetailsBinding
    private var car: Car? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the car object from the intent using Parcelable
        car = intent.getParcelableExtra("car", Car::class.java)
        car?.let { displayCarDetails(it) }

        setupClickListeners()
    }

    private fun displayCarDetails(car: Car) {
        // Load image from drawable resources based on car.image string
        val imageResId = when (car.image) {
            "bmw_x5.jpg" -> R.drawable.bmw_x5
            "mercedes_c_class.jpg" -> R.drawable.mercedes_c_class
            "audi_a4.jpg" -> R.drawable.audi_a4
            else -> R.drawable.logo2
        }
        binding.carImage.setImageResource(imageResId)
        binding.carName.text = car.name
        binding.carMileage.text = car.mileage
        binding.carPrice.text = "$${car.price}"
    }

    private fun setupClickListeners() {
        binding.purchaseButton.setOnClickListener {
            // Create purchase via API
            car?.let { createPurchase(it) }
        }

        binding.bookButton.setOnClickListener {
            // Create booking via API
            car?.let { createBooking(it) }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun createPurchase(car: Car) {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userEmail = sharedPref.getString("user_email", "") ?: ""

        if (userEmail.isEmpty()) {
            // Handle not logged in
            return
        }

        val purchase = Purchase(
            purchaseID = "P${System.currentTimeMillis()}", // Generate unique ID
            vehiclename = car.name,
            purchasedate = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault()).format(java.util.Date()),
            email = userEmail,
            status = "Processing"
        )

        // TODO: Implement API call to create purchase
        // For now, just show a toast
        android.widget.Toast.makeText(this, "Purchase created successfully!", android.widget.Toast.LENGTH_SHORT).show()
        finish()
    }

    private fun createBooking(car: Car) {
        val sharedPref = getSharedPreferences("user_prefs", MODE_PRIVATE)
        val userEmail = sharedPref.getString("user_email", "") ?: ""

        if (userEmail.isEmpty()) {
            // Handle not logged in
            return
        }

        val currentDate = java.util.Date()
        val endDate = java.util.Calendar.getInstance().apply {
            time = currentDate
            add(java.util.Calendar.DAY_OF_MONTH, 7) // Default 7 days booking
        }.time

        val dateFormat = java.text.SimpleDateFormat("yyyy-MM-dd", java.util.Locale.getDefault())

        val booking = Booking(
            bookingsID = "B${System.currentTimeMillis()}", // Generate unique ID
            vehiclename = car.name,
            pickupdate = dateFormat.format(currentDate),
            returndate = dateFormat.format(endDate),
            email = userEmail,
            status = "Confirmed"
        )

        // TODO: Implement API call to create booking
        // For now, just show a toast
        android.widget.Toast.makeText(this, "Booking created successfully!", android.widget.Toast.LENGTH_SHORT).show()
        finish()
    }
}
