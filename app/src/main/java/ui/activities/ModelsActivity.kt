package com.example.cruisemastersmad.ui.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cruisemastersmad.activities.CarDetailsActivity
import com.example.cruisemastersmad.databinding.ActivityModelsBinding
import com.example.cruisemastersmad.ui.adapters.CarAdapter
import ui.models.Car

class ModelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModelsBinding
    private lateinit var carAdapter: CarAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter(getSampleCars()) { car ->
            // Handle car item click - navigate to car details
            val intent = Intent(this, CarDetailsActivity::class.java)
            intent.putExtra("car", car)
            startActivity(intent)
        }

        binding.carsRecyclerView.apply {
            layoutManager = GridLayoutManager(this@ModelsActivity, 2)
            adapter = carAdapter
        }
    }

    private fun setupClickListeners() {
        binding.backButton.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
        }

        binding.searchView.setOnQueryTextListener(object : androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                filterCars(newText)
                return true
            }
        })
    }

    private fun filterCars(query: String?) {
        val filteredCars = if (query.isNullOrEmpty()) {
            getSampleCars()
        } else {
            getSampleCars().filter { car ->
                car.name.contains(query, true) || car.mileage.contains(query, true)
            }
        }
        carAdapter.updateList(filteredCars)
    }

    private fun getSampleCars(): List<Car> {
        return listOf(
            Car(
                id = 1,
                name = "BMW M8",
                mileage = "15 km/l",
                price = 250,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 2,
                name = "Mercedes AMG",
                mileage = "12 km/l",
                price = 280,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 3,
                name = "Audi R8",
                mileage = "10 km/l",
                price = 300,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 4,
                name = "Porsche 911",
                mileage = "11 km/l",
                price = 320,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 5,
                name = "Lamborghini Huracan",
                mileage = "8 km/l",
                price = 450,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 6,
                name = "Ferrari F8",
                mileage = "9 km/l",
                price = 420,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 7,
                name = "Tesla Model S",
                mileage = "Electric",
                price = 200,
                imageResId = R.drawable.logo2
            ),
            Car(
                id = 8,
                name = "Range Rover",
                mileage = "14 km/l",
                price = 180,
                imageResId = R.drawable.logo2
            )
        )
    }
}