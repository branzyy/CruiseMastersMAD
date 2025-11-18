package com.example.cruisemastersmad.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.cruisemastersmad.models.Booking
import com.example.cruisemastersmad.models.Car
import com.example.cruisemastersmad.models.Purchase
import com.example.cruisemastersmad.models.User
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(
    entities = [User::class, Car::class, Purchase::class, Booking::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun carDao(): CarDao
    abstract fun purchaseDao(): PurchaseDao
    abstract fun bookingDao(): BookingDao

    companion object {
        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
                    "cruisemasters_database"
                )
                .addCallback(DatabaseCallback())
                .build()
                INSTANCE = instance
                instance
            }
        }
    }

    private class DatabaseCallback : RoomDatabase.Callback() {
        override fun onCreate(db: SupportSQLiteDatabase) {
            super.onCreate(db)
            INSTANCE?.let { database ->
                CoroutineScope(Dispatchers.IO).launch {
                    populateDatabase(database.carDao())
                }
            }
        }

        suspend fun populateDatabase(carDao: CarDao) {
            // Pre-populate with some cars
            val cars = listOf(
                Car("1", "BMW M8 Competition", "15000", "$120000", "bmw_x5.jpg"),
                Car("2", "Mercedes-AMG GT", "12000", "$135000", "mercedes_c_class.jpg"),
                Car("3", "Audi R8", "18000", "$150000", "audi_a4.jpg"),
                Car("4", "Porsche 911 Turbo S", "8000", "$200000", "bmw_x5.jpg"),
                Car("5", "Audi A4", "25000", "$45000", "audi_a4.jpg"),
                Car("6", "BMW X5", "35000", "$55000", "bmw_x5.jpg"),
                Car("7", "Mercedes C-Class", "22000", "$48000", "mercedes_c_class.jpg"),
                Car("8", "Chevrolet Camaro", "10000", "$35000", "chevrolet_camaro.jpg"),
                Car("9", "Ford Mustang", "28000", "$42000", "ford_mustang.jpg"),
                Car("10", "Honda Civic", "15000", "$25000", "honda_civic.jpg")
            )
            carDao.insertCars(cars)
        }
    }
}
