package com.example.cruisemastersmad.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cruisemastersmad.database.AppDatabase
import com.example.cruisemastersmad.models.Car
import kotlinx.coroutines.launch

class CarViewModel(application: Application) : AndroidViewModel(application) {
    private val carDao = AppDatabase.getDatabase(application).carDao()

    private val _cars = MutableLiveData<List<Car>>()
    val cars: LiveData<List<Car>> get() = _cars

    fun loadCars() {
        viewModelScope.launch {
            val carList = carDao.getAllCars()
            _cars.postValue(carList)
        }
    }

    fun getCarById(carId: String, onResult: (Car?) -> Unit) {
        viewModelScope.launch {
            val car = carDao.getCarById(carId)
            onResult(car)
        }
    }
}
