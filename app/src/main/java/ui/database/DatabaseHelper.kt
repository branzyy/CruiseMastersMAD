package ui.database

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "CruiseMasters.db"
        private const val DATABASE_VERSION = 1

        // Table names
        const val TABLE_USERS = "users"
        const val TABLE_CRUISES = "cruises"
        const val TABLE_PURCHASES = "purchases"
        const val TABLE_BOOKINGS = "bookings"
        const val TABLE_REVIEWS = "reviews"

        // Common column names
        const val COLUMN_ID = "id"
        const val COLUMN_CREATED_AT = "created_at"

        // Users table columns
        const val COLUMN_USERNAME = "username"
        const val COLUMN_EMAIL = "email"
        const val COLUMN_PASSWORD = "password"
        const val COLUMN_FULL_NAME = "full_name"
        const val COLUMN_PHONE = "phone"
        const val COLUMN_PROFILE_IMAGE = "profile_image"

        // Cruises table columns
        const val COLUMN_CRUISE_NAME = "cruise_name"
        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PRICE = "price"
        const val COLUMN_IMAGE_URL = "image_url"
        const val COLUMN_DURATION = "duration"
        const val COLUMN_DEPARTURE_PORT = "departure_port"
        const val COLUMN_DESTINATIONS = "destinations"
        const val COLUMN_CAPACITY = "capacity"
        const val COLUMN_AVAILABLE_SPOTS = "available_spots"
        const val COLUMN_DEPARTURE_DATE = "departure_date"
        const val COLUMN_RETURN_DATE = "return_date"

        // Purchases table columns
        const val COLUMN_USER_ID = "user_id"
        const val COLUMN_ITEM_NAME = "item_name"
        const val COLUMN_AMOUNT = "amount"
        const val COLUMN_PURCHASE_DATE = "purchase_date"
        const val COLUMN_STATUS = "status"

        // Bookings table columns
        const val COLUMN_CRUISE_ID = "cruise_id"
        const val COLUMN_BOOKING_DATE = "booking_date"
        const val COLUMN_GUESTS_COUNT = "guests_count"
        const val COLUMN_TOTAL_PRICE = "total_price"
        const val COLUMN_BOOKING_STATUS = "booking_status"

        // Reviews table columns
        const val COLUMN_RATING = "rating"
        const val COLUMN_COMMENT = "comment"
        const val COLUMN_REVIEW_DATE = "review_date"
    }

    override fun onCreate(db: SQLiteDatabase) {
        createUsersTable(db)
        createCruisesTable(db)
        createPurchasesTable(db)
        createBookingsTable(db)
        createReviewsTable(db)
        insertSampleData(db)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_REVIEWS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_BOOKINGS")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_PURCHASES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CRUISES")
        db.execSQL("DROP TABLE IF EXISTS $TABLE_USERS")
        onCreate(db)
    }

    private fun createUsersTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_USERS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USERNAME TEXT UNIQUE NOT NULL,
                $COLUMN_EMAIL TEXT UNIQUE NOT NULL,
                $COLUMN_PASSWORD TEXT NOT NULL,
                $COLUMN_FULL_NAME TEXT NOT NULL,
                $COLUMN_PHONE TEXT,
                $COLUMN_PROFILE_IMAGE TEXT,
                $COLUMN_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun createCruisesTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_CRUISES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_CRUISE_NAME TEXT NOT NULL,
                $COLUMN_DESCRIPTION TEXT,
                $COLUMN_PRICE REAL NOT NULL,
                $COLUMN_IMAGE_URL TEXT,
                $COLUMN_DURATION INTEGER NOT NULL,
                $COLUMN_DEPARTURE_PORT TEXT NOT NULL,
                $COLUMN_DESTINATIONS TEXT NOT NULL,
                $COLUMN_CAPACITY INTEGER NOT NULL,
                $COLUMN_AVAILABLE_SPOTS INTEGER NOT NULL,
                $COLUMN_DEPARTURE_DATE TEXT NOT NULL,
                $COLUMN_RETURN_DATE TEXT NOT NULL,
                $COLUMN_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun createPurchasesTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_PURCHASES (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_ITEM_NAME TEXT NOT NULL,
                $COLUMN_AMOUNT REAL NOT NULL,
                $COLUMN_PURCHASE_DATE TEXT NOT NULL,
                $COLUMN_STATUS TEXT NOT NULL,
                $COLUMN_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS($COLUMN_ID)
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun createBookingsTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_BOOKINGS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_CRUISE_ID INTEGER NOT NULL,
                $COLUMN_BOOKING_DATE TEXT NOT NULL,
                $COLUMN_GUESTS_COUNT INTEGER NOT NULL,
                $COLUMN_TOTAL_PRICE REAL NOT NULL,
                $COLUMN_BOOKING_STATUS TEXT NOT NULL,
                $COLUMN_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS($COLUMN_ID),
                FOREIGN KEY ($COLUMN_CRUISE_ID) REFERENCES $TABLE_CRUISES($COLUMN_ID)
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun createReviewsTable(db: SQLiteDatabase) {
        val createTable = """
            CREATE TABLE $TABLE_REVIEWS (
                $COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT,
                $COLUMN_USER_ID INTEGER NOT NULL,
                $COLUMN_CRUISE_ID INTEGER NOT NULL,
                $COLUMN_RATING REAL NOT NULL,
                $COLUMN_COMMENT TEXT,
                $COLUMN_REVIEW_DATE TEXT NOT NULL,
                $COLUMN_CREATED_AT DATETIME DEFAULT CURRENT_TIMESTAMP,
                FOREIGN KEY ($COLUMN_USER_ID) REFERENCES $TABLE_USERS($COLUMN_ID),
                FOREIGN KEY ($COLUMN_CRUISE_ID) REFERENCES $TABLE_CRUISES($COLUMN_ID)
            )
        """.trimIndent()
        db.execSQL(createTable)
    }

    private fun insertSampleData(db: SQLiteDatabase) {
        // Insert sample users
        insertSampleUsers(db)

        // Insert sample cruises
        insertSampleCruises(db)

        // Insert sample purchases
        insertSamplePurchases(db)
    }

    private fun insertSampleUsers(db: SQLiteDatabase) {
        val users = listOf(
            ContentValues().apply {
                put(COLUMN_USERNAME, "john_doe")
                put(COLUMN_EMAIL, "john@example.com")
                put(COLUMN_PASSWORD, "password123")
                put(COLUMN_FULL_NAME, "John Doe")
                put(COLUMN_PHONE, "+1234567890")
            },
            ContentValues().apply {
                put(COLUMN_USERNAME, "jane_smith")
                put(COLUMN_EMAIL, "jane@example.com")
                put(COLUMN_PASSWORD, "password123")
                put(COLUMN_FULL_NAME, "Jane Smith")
                put(COLUMN_PHONE, "+0987654321")
            }
        )

        users.forEach { userValues ->
            db.insert(TABLE_USERS, null, userValues)
        }
    }

    private fun insertSampleCruises(db: SQLiteDatabase) {
        val cruises = listOf(
            ContentValues().apply {
                put(COLUMN_CRUISE_NAME, "Caribbean Paradise")
                put(COLUMN_DESCRIPTION, "Enjoy 7 days in the beautiful Caribbean islands")
                put(COLUMN_PRICE, 1500.0)
                put(COLUMN_IMAGE_URL, "https://example.com/caribbean.jpg")
                put(COLUMN_DURATION, 7)
                put(COLUMN_DEPARTURE_PORT, "Miami, Florida")
                put(COLUMN_DESTINATIONS, "Bahamas,Jamaica,Cayman Islands")
                put(COLUMN_CAPACITY, 200)
                put(COLUMN_AVAILABLE_SPOTS, 150)
                put(COLUMN_DEPARTURE_DATE, "2024-06-15")
                put(COLUMN_RETURN_DATE, "2024-06-22")
            },
            ContentValues().apply {
                put(COLUMN_CRUISE_NAME, "Mediterranean Adventure")
                put(COLUMN_DESCRIPTION, "Explore the historic Mediterranean coast")
                put(COLUMN_PRICE, 2200.0)
                put(COLUMN_IMAGE_URL, "https://example.com/mediterranean.jpg")
                put(COLUMN_DURATION, 10)
                put(COLUMN_DEPARTURE_PORT, "Barcelona, Spain")
                put(COLUMN_DESTINATIONS, "Italy,France,Greece")
                put(COLUMN_CAPACITY, 300)
                put(COLUMN_AVAILABLE_SPOTS, 200)
                put(COLUMN_DEPARTURE_DATE, "2024-07-01")
                put(COLUMN_RETURN_DATE, "2024-07-11")
            }
        )

        cruises.forEach { cruiseValues ->
            db.insert(TABLE_CRUISES, null, cruiseValues)
        }
    }

    private fun insertSamplePurchases(db: SQLiteDatabase) {
        val purchases = listOf(
            ContentValues().apply {
                put(COLUMN_USER_ID, 1)
                put(COLUMN_ITEM_NAME, "Caribbean Paradise Cruise")
                put(COLUMN_AMOUNT, 1500.0)
                put(COLUMN_PURCHASE_DATE, "2024-01-15")
                put(COLUMN_STATUS, "Completed")
            },
            ContentValues().apply {
                put(COLUMN_USER_ID, 1)
                put(COLUMN_ITEM_NAME, "Travel Insurance")
                put(COLUMN_AMOUNT, 200.0)
                put(COLUMN_PURCHASE_DATE, "2024-01-16")
                put(COLUMN_STATUS, "Completed")
            }
        )

        purchases.forEach { purchaseValues ->
            db.insert(TABLE_PURCHASES, null, purchaseValues)
        }
    }
}