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
        const val TABLE_CAR = "Car"
        const val TABLE_PURCHASES = "Purchase"
        const val TABLE_BOOKINGS = "Booking"

        // Users table columns
        const val COLUMN_FIRST_NAME = "first_name"
        const val COLUMN_LAST_NAME = "last_name"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_VERIFICATION_CODE = "verification_code"
        const val COLUMN_VERIFICATION_EXPIRY = "verification_expiry"
        const val COLUMN_ID_NUMBER = "id_number"

        // Car table columns
        const val COLUMN_Car_NAME = "Car_name"
        const val COLUMN_Car_YEAR = "Car_year"
        const val COLUMN_Car_MILEAGE = "Car_mileage"
        const val COLUMN_Car_PRICE = "Car_price"
        const val COLUMN_Car_ID = "Car_id"
        const val COLUMN_Car_image = "Car_image"


        // Purchases table columns
        const val COLUMN_PURCHASE_ID = "user_id"
        const val COLUMN_VEHICLE_NAME = "Car_name"
        const val COLUMN_User_Email = "email"
        const val COLUMN_PURCHASE_DATE = "purchase_date"
        const val COLUMN_STATUS = "status"

        // Bookings table columns
        const val COLUMN_CAR_NAME = "Car_name"
        const val COLUMN_BOOKING_ID = "booking_id"
        const val COLUMN_PICKUP_DATE = "pickup_date"
        const val COLUMN_RETURN_DATE = "return_date"
        const val COLUMN_USER_EMAIL = "email"
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
        val createTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID_NUMBER INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_FIRST_NAME TEXT UNIQUE NOT NULL,
                $COLUMN_LAST_NAME TEXT NOT NULL,
                ${ui.database.COLUMN_EMAIL} TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_EMAIL TEXT NOT NULL,
                $COLUMN_VERIFICATION_CODE TEXT,
                ${COLUMN_VERIFICATION_EXPIRY}RIFICATION_EXPIRY TEXT
              
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun createCarTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_CAR (
                $COLUMN_Car_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                ${COLUMN_Car_NAME}_NAME TEXT NOT NULL,
                ${COLUMN_Car_PRICE}PRICE REAL NOT NULL,
                ${COLUMN_Car_image}IMAGE_URL TEXT,
                ${COLUMN_Car_MILEAGE}DURATION INTEGER NOT NULL,
                $COLUMN_Car_YEAR INTEGER NOT NULL
               
            )
        """.trimIndent()
        db.execSQL(createTable)
    }
##
    private fun createPurchasesTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_PURCHASES (
                ${COLUMN_PURCHASE_ID}ID INTEGER PRIMARY KEY AUTOINCREMENT,
                ${COLUMN_User_Email}EMAIL TEXT NOT NULL,
                ${COLUMN_VEHICLE_NAME}_NAME TEXT NOT NULL,
                $COLUMN_PURCHASE_DATE TEXT NOT NULL,
                $COLUMN_STATUS TEXT NOT NULL
                
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun createBookingsTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_BOOKINGS (
                ${COLUMN_BOOKING_ID}ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CAR_NAME INTEGER NOT NULL,
                $COLUMN_RETURN_DATE INTEGER NOT NULL,
                ${COLUMN_PICKUP_DATE}_DATE TEXT NOT NULL,
                $COLUMN_USER_EMAIL TEXT NOT NULL,
                $COLUMN_BOOKING_STATUS TEXT NOT NULL
               
            )
        """.trimIndent()
        db.execSQL(createTable)
    }





}

const val COLUMN_EMAIL = "email"