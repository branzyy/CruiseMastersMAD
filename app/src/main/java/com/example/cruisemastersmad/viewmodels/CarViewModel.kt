package com.example.cruisemastersmad.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cruisemastersmad.api.ApiService
import com.example.cruisemastersmad.database.AppDatabase
import com.example.cruisemastersmad.models.Car
import kotlinx.coroutines.launch
import retrofit2.Response

class CarViewModel(application: Application) : AndroidViewModel(application) {
    private val carDao = AppDatabase.getDatabase(application).carDao()
    private val apiService = ApiService.create()

    private val _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> get() = _cars

    fun loadCars() {
        viewModelScope.launch {
            try {
                // First try to load from API and sync to local DB
                val response: Response<List<Car>> = apiService.getCars()
                if (response.isSuccessful) {
                    val carsFromApi = response.body() ?: emptyList()
                    // Filter cars to only include those with available drawables
                    val availableCars = carsFromApi.filter { car ->
                        isImageAvailable(car.image)
                    }
                    // Insert into local database
                    carDao.insertCars(availableCars)
                    _cars.postValue(availableCars)
                } else {
                    // Fallback to local database
                    val carList = carDao.getAllCars()
                    _cars.postValue(carList)
                }
            } catch (e: Exception) {
                // Fallback to local database on error
                val carList = carDao.getAllCars()
                _cars.postValue(carList)
            }
        }
    }

    private fun isImageAvailable(imageName: String): Boolean {
        // List of available car images in drawable resources
        val availableImages = setOf(
            "audi_a4.jpg",
            "bmw_x5.jpg",
            "cadillac_escalade.jpg",
            "chevrolet_camaro.jpg",
            "ford_mustang.jpg",
            "honda_civic.jpg",
            "hyundai_elantra.jpg",
            "jeep_wrangler.jpg",
            "kia_k5.jpg",
            "lexus_rx350.jpg",
            "mazda_cx5.jpg",
            "mercedes_c_class.jpg",
            "nissan_altima.jpg",
            "subaru_outback.jpg",
            "toyota_camry.jpg",
            "vw_arteon.jpg"
        )
        return availableImages.contains(imageName)
    }

    fun getCarById(carId: String, onResult: (Car?) -> Unit) {
        viewModelScope.launch {
            val car = carDao.getCarById(carId)
            onResult(car)
        }
    }
}
