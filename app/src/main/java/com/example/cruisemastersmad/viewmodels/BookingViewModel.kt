package com.example.cruisemastersmad.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cruisemastersmad.database.AppDatabase
import com.example.cruisemastersmad.models.Booking
import kotlinx.coroutines.launch

class BookingViewModel(application: Application) : AndroidViewModel(application) {
    private val bookingDao = AppDatabase.getDatabase(application).bookingDao()

    private val _bookings = MutableLiveData<List<Booking>>()
    val bookings: LiveData<List<Booking>> get() = _bookings

    fun loadBookings(email: String) {
        viewModelScope.launch {
            val bookingList = bookingDao.getBookingsByEmail(email)
            _bookings.postValue(bookingList)
        }
    }

    fun bookCar(booking: Booking, onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val id = bookingDao.insertBooking(booking)
            onResult(id)
        }
    }
}
