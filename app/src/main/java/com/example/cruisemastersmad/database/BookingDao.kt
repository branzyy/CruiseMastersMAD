package com.example.cruisemastersmad.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cruisemastersmad.models.Booking

@Dao
interface BookingDao {
    @Insert
    suspend fun insertBooking(booking: Booking): Long

    @Query("SELECT * FROM bookings WHERE email = :email ORDER BY pickupdate DESC")
    suspend fun getBookingsByEmail(email: String): List<Booking>

    @Query("SELECT * FROM bookings")
    suspend fun getAllBookings(): List<Booking>
}
