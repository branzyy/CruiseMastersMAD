package com.example.cruisemastersmad.ui.activities

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruisemastersmad.databinding.ActivityModelsBinding
import com.example.cruisemastersmad.ui.adapters.CarAdapter
import com.example.cruisemastersmad.ui.models.Car

class ModelsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityModelsBinding
    private lateinit var carAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        loadCars()
        setupToolbar()
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter(cars) { car ->
            val options = arrayOf("Purchase", "Book")
            androidx.appcompat.app.AlertDialog.Builder(this)
                .setTitle("Choose an action for ${car.name}")
                .setItems(options) { _, which ->
                    when (which) {
                        0 -> {
                            addPurchase(car)
                            Toast.makeText(this, "${car.name} purchased successfully!", Toast.LENGTH_SHORT).show()
                        }
                        1 -> {
                            addBooking(car)
                            Toast.makeText(this, "${car.name} booked successfully!", Toast.LENGTH_SHORT).show()
                        }
                    }
                }
                .show()
        }

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(this@ModelsActivity)
            adapter = carAdapter
        }
    }


    private fun loadCars() {
        val sampleCars = listOf(
            Car(1, "BMW X5", "2023", "15,000 km", "$65,000"),
            Car(2, "Mercedes C-Class", "2022", "12,000 km", "$55,000"),
            Car(3, "Audi Q7", "2023", "8,000 km", "$75,000"),
            Car(4, "Toyota Land Cruiser", "2022", "20,000 km", "$85,000"),
            Car(5, "Range Rover Sport", "2023", "5,000 km", "$95,000")
        )

        carAdapter.submitList(sampleCars)
    }

    private fun addPurchase(car: Car) {
        val sharedPref = getSharedPreferences("user_purchases", MODE_PRIVATE)
        val purchases = sharedPref.getStringSet("purchases", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        val purchaseData = "${car.id}|${car.name}|${System.currentTimeMillis()}"
        purchases.add(purchaseData)

        with(sharedPref.edit()) {
            putStringSet("purchases", purchases)
            apply()
        }
    }

    private fun addBooking(car: Car) {
        val sharedPref = getSharedPreferences("user_bookings", MODE_PRIVATE)
        val bookings = sharedPref.getStringSet("bookings", mutableSetOf())?.toMutableSet() ?: mutableSetOf()

        val bookingData = "${car.id}|${car.name}|${System.currentTimeMillis()}"
        bookings.add(bookingData)

        with(sharedPref.edit()) {
            putStringSet("bookings", bookings)
            apply()
        }
    }

    private fun setupToolbar() {
        binding.toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
    }
}