package com.example.cruisemastersmad.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "bookings")
data class Booking(
    @PrimaryKey
    val bookingsID: String,
    val vehiclename: String,
    val pickupdate: String,
    val returndate: String,
    val email: String,
    val status: String
)
