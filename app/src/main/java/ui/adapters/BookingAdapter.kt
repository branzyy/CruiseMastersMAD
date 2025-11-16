package com.example.cruisemastersmad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cruisemastersmad.R
import ui.models.Booking

class BookingAdapter(private val bookings: List<Booking>) :
    RecyclerView.Adapter<BookingAdapter.BookingViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_booking, parent, false)
        return BookingViewHolder(view)
    }

    override fun onBindViewHolder(holder: BookingViewHolder, position: Int) {
        val booking = bookings[position]
        holder.bind(booking)
    }

    override fun getItemCount(): Int = bookings.size

    class BookingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carNameTextView: TextView = itemView.findViewById(R.id.carNameTextView)
        private val startDateTextView: TextView = itemView.findViewById(R.id.startDateTextView)
        private val endDateTextView: TextView = itemView.findViewById(R.id.endDateTextView)
        private val bookingIdTextView: TextView = itemView.findViewById(R.id.bookingIdTextView)

        fun bind(booking: Booking) {
            carNameTextView.text = booking.carName
            startDateTextView.text = "Start: ${booking.startDate}"
            endDateTextView.text = "End: ${booking.endDate}"
            bookingIdTextView.text = "Booking #${booking.id}"
        }
    }
}