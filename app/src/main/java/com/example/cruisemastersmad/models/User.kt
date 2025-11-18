package com.example.cruisemastersmad.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity(tableName = "users")
data class User(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
    @SerializedName("name")
    val name: String,
    val email: String,
    val password: String
)
