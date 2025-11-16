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
            "bmw_m8.jpg" -> R.drawable.bmw_x5
            "mercedes_amg.jpg" -> R.drawable.mercedes_c_class
            "audi_r8.jpg" -> R.drawable.audi_a4
            "porsche_911.jpg" -> R.drawable.bmw_x5 // Use existing drawable
            "lamborghini_huracan.jpg" -> R.drawable.bmw_x5 // Use existing drawable
            "ferrari_f8.jpg" -> R.drawable.bmw_x5 // Use existing drawable
            "tesla_model_s.jpg" -> R.drawable.bmw_x5 // Use existing drawable
            "range_rover.jpg" -> R.drawable.bmw_x5 // Use existing drawable
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
