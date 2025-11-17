package com.example.cruisemastersmad.ui.activities

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.cruisemastersmad.R
import com.example.cruisemastersmad.databinding.ActivityCarDetailsBinding
import com.example.cruisemastersmad.ui.models.Car

class CarDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityCarDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCarDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Get the car object from the intent using Parcelable
        val car = intent.getParcelableExtra("car", Car::class.java)
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
        binding.bookButton.setOnClickListener {
            // Navigate to booking screen
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }
}
