package com.example.cruisemastersmad.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cruisemastersmad.R
import com.example.cruisemastersmad.databinding.ActivityCarDetailsBinding
import com.example.cruisemastersmad.models.Car

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
            // Navigate to purchase screen
            val intent = Intent(this, com.example.cruisemastersmad.activities.PurchaseActivity::class.java)
            intent.putExtra("car", car)
            startActivity(intent)
        }

        binding.bookButton.setOnClickListener {
            // Navigate to booking screen
            val intent = Intent(this, com.example.cruisemastersmad.activities.BookingActivity::class.java)
            intent.putExtra("car", car)
            startActivity(intent)
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
