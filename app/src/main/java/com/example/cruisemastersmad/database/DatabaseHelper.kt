package ui.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CruiseMasters.db"
        private const val DATABASE_VERSION = 1

        // Table names
        const val TABLE_USERS = "user"
        const val TABLE_CAR = "car"
        const val TABLE_PURCHASES = "purchase"
        const val TABLE_BOOKINGS = "booking"

        // Users table columns
        const val COLUMN_ID_NUMBER = "id_number"
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_VERIFICATION_CODE = "verification_code"
        const val COLUMN_VERIFICATION_EXPIRY = "verification_expiry"

        // Car table columns
        const val COLUMN_CAR_ID = "car_id"
        const val COLUMN_CAR_NAME = "car_name"
        const val COLUMN_CAR_YEAR = "car_year"
        const val COLUMN_CAR_MILEAGE = "car_mileage"
        const val COLUMN_CAR_PRICE = "car_price"
        const val COLUMN_CAR_IMAGE = "car_image"

        // Purchases table columns
        const val COLUMN_PURCHASE_ID = "purchase_id"
        const val COLUMN_PURCHASE_USER_EMAIL = "user_email"
        const val COLUMN_PURCHASE_VEHICLE_NAME = "vehicle_name"
        const val COLUMN_PURCHASE_DATE = "purchase_date"
        const val COLUMN_PURCHASE_STATUS = "status"

        // Bookings table columns
        const val COLUMN_BOOKING_ID = "booking_id"
        const val COLUMN_BOOKING_CAR_NAME = "car_name"
        const val COLUMN_PICKUP_DATE = "pickup_date"
        const val COLUMN_RETURN_DATE = "return_date"
        const val COLUMN_BOOKING_EMAIL = "user_email"
        const val COLUMN_BOOKING_STATUS = "booking_status"
    }

    override fun onCreate(db: SQLiteDatabase) {
        createUsersTable(db)
        createCarTable(db)
        createPurchasesTable(db)
        createBookingsTable(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKINGS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PURCHASES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CAR")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    private fun createUsersTable(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID_NUMBER INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FIRST_NAME TEXT NOT NULL,
                $COLUMN_LAST_NAME TEXT NOT NULL,
                $COLUMN_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_VERIFICATION_CODE TEXT,
                $COLUMN_VERIFICATION_EXPIRY TEXT
            )
        """.trimIndent()
        db.execSQL(query)
    }

    private fun createCarTable(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_CAR (
                $COLUMN_CAR_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CAR_NAME TEXT NOT NULL,
                $COLUMN_CAR_PRICE REAL NOT NULL,
                $COLUMN_CAR_IMAGE TEXT,
                $COLUMN_CAR_MILEAGE INTEGER NOT NULL,
                $COLUMN_CAR_YEAR INTEGER NOT NULL
            )
        """.trimIndent()
        db.execSQL(query)
    }

    private fun createPurchasesTable(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_PURCHASES (
                $COLUMN_PURCHASE_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_PURCHASE_USER_EMAIL TEXT NOT NULL,
                $COLUMN_PURCHASE_VEHICLE_NAME TEXT NOT NULL,
                $COLUMN_PURCHASE_DATE TEXT NOT NULL,
                $COLUMN_PURCHASE_STATUS TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(query)
    }

    private fun createBookingsTable(db: SQLiteDatabase) {
        val query = """
            CREATE TABLE $TABLE_BOOKINGS (
                $COLUMN_BOOKING_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_BOOKING_CAR_NAME TEXT NOT NULL,
                $COLUMN_PICKUP_DATE TEXT NOT NULL,
                $COLUMN_RETURN_DATE TEXT NOT NULL,
                $COLUMN_BOOKING_EMAIL TEXT NOT NULL,
                $COLUMN_BOOKING_STATUS TEXT NOT NULL
            )
        """.trimIndent()
        db.execSQL(query)
    }
}
