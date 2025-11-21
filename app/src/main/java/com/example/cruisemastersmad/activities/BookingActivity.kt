package com.example.cruisemastersmad.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruisemastersmad.databinding.ActivityBookingBinding
import com.example.cruisemastersmad.ui.adapters.BookingAdapter
import com.example.cruisemastersmad.models.Booking

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
                bookingsID = "1",
                vehiclename = "BMW M8",
                pickupdate = "2024-01-15",
                returndate = "2024-01-20",
                email = "user@example.com",
                status = "Confirmed"
            ),
            Booking(
                bookingsID = "2",
                vehiclename = "Mercedes AMG",
                pickupdate = "2024-02-01",
                returndate = "2024-02-05",
                email = "user@example.com",
                status = "Confirmed"
            ),
            Booking(
                bookingsID = "3",
                vehiclename = "Audi R8",
                pickupdate = "2024-03-10",
                returndate = "2024-03-15",
                email = "user@example.com",
                status = "Confirmed"
            )
        )
    }
}