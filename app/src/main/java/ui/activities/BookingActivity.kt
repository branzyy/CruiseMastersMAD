package com.example.cruisemastersmad.ui.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruisemastersmad.databinding.ActivityBookingBinding
import com.example.cruisemastersmad.ui.adapters.BookingAdapter
import ui.models.Booking

class BookingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityBookingBinding
    private lateinit var bookingAdapter: BookingAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityBookingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        val bookings = getSampleBookings()
        bookingAdapter = BookingAdapter(bookings)
        binding.bookingsRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@BookingActivity)
            adapter = bookingAdapter
        }
    }

    private fun setupClickListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun getSampleBookings(): List<Booking> {
        return listOf(
            Booking(
                id = 1,
                userId = 1,
                carName = "BMW M8",
                startDate = "2024-01-15",
                endDate = "2024-01-20",

            ),
            Booking(
                id = 2,
                userId = 2,
                carName = "Mercedes AMG",
                startDate = "2024-02-01",
                endDate = "2024-02-05",

            ),
            Booking(
                id = 3,
                userId = 1,
                carName = "Audi R8",
                startDate = "2024-03-10",
                endDate = "2024-03-15",
            )
        )
    }
}