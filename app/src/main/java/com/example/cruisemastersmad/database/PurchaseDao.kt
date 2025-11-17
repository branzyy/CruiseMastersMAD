package com.example.cruisemastersmad.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.cruisemastersmad.models.Purchase

@Dao
interface PurchaseDao {
    @Insert
    suspend fun insertPurchase(purchase: Purchase): Long

    @Query("SELECT * FROM purchases WHERE email = :email ORDER BY purchasedate DESC")
    suspend fun getPurchasesByEmail(email: String): List<Purchase>

    @Query("SELECT * FROM purchases")
    suspend fun getAllPurchases(): List<Purchase>
}
