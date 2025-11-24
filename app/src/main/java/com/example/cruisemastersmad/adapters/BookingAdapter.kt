package com.example.cruisemastersmad.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cruisemastersmad.R
import com.example.cruisemastersmad.models.Booking

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
        private val carNameTextView: TextView = itemView.findViewById(R.id.bookingCarName)
        private val periodTextView: TextView = itemView.findViewById(R.id.bookingPeriod)
        private val totalPriceTextView: TextView = itemView.findViewById(R.id.bookingTotalPrice)
        private val statusTextView: TextView = itemView.findViewById(R.id.bookingStatus)

        fun bind(booking: Booking) {
            carNameTextView.text = booking.vehiclename
            periodTextView.text = "${booking.pickupdate} - ${booking.returndate}"
            totalPriceTextView.text = "$${booking.bookingsID}"
            statusTextView.text = booking.status
        }
    }
}