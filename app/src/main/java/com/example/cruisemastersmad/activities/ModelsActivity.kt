package com.example.cruisemastersmad.activities

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.example.cruisemastersmad.databinding.ActivityModelsBinding
import com.example.cruisemastersmad.ui.adapters.CarAdapter
import com.example.cruisemastersmad.models.Car as CarModel
import com.example.cruisemastersmad.ui.viewmodels.CarViewModel

class ModelsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityModelsBinding
    private lateinit var carAdapter: CarAdapter
    private lateinit var carViewModel: CarViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityModelsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        carViewModel = ViewModelProvider(this).get(CarViewModel::class.java)

        setupRecyclerView()
        setupClickListeners()
        loadCars()
    }

    private fun setupRecyclerView() {
        carAdapter = CarAdapter(emptyList()) { car ->
            // Handle car item click - navigate to car details
            val intent = Intent(this, com.example.cruisemastersmad.activities.CarDetailsActivity::class.java)
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
    }

    private fun loadCars() {
        carViewModel.loadCars()
        carViewModel.cars.observe(this) { cars ->
            carAdapter.updateList(cars)
        }
    }
}
