package com.example.cruisemastersmad.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    val firstname: String,
    val lastname: String,
    val email: String,
    val password: String
)
