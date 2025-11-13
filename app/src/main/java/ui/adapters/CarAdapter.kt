package com.example.cruisemastersmad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.cruisemastersmad.R
import com.example.cruisemastersmad.models.Car

class CarAdapter(
    private val cars: List<Car>,
    private val onItemClick: (Car) -> Unit  // single-click listener
) : RecyclerView.Adapter<CarAdapter.CarViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_car, parent, false)
        return CarViewHolder(view)
    }

    override fun onBindViewHolder(holder: CarViewHolder, position: Int) {
        holder.bind(cars[position])
    }

    override fun getItemCount() = cars.size

    inner class CarViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carImage: ImageView = itemView.findViewById(R.id.carImage)
        private val carName: TextView = itemView.findViewById(R.id.carName)
        private val carBrand: TextView = itemView.findViewById(R.id.carBrand)
        private val carPrice: TextView = itemView.findViewById(R.id.carPrice)

        fun bind(car: Car) {
            carName.text = car.name
            carBrand.text = car.brand
            carPrice.text = "$${car.price}/day"
            carImage.setImageResource(car.imageResId)

            // Single click triggers the listener in the activity
            itemView.setOnClickListener { onItemClick(car) }
        }
    }
}
