package com.example.cruisemastersmad.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "purchases")
data class Purchase(
    @PrimaryKey
    val purchaseID: String,
    val vehiclename: String,
    val purchasedate: String,
    val email: String,
    val status: String
)
