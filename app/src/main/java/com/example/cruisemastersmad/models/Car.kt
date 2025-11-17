package com.example.cruisemastersmad.models

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Entity(tableName = "cars")
@Parcelize
data class Car(
    @PrimaryKey
    val carId: String,
    val name: String,
    val mileage: String,
    val price: String,
    val image: String
) : Parcelable
