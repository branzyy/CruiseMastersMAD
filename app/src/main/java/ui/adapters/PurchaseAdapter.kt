package com.example.cruisemastersmad.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cruisemastersmad.R
import ui.models.Purchase

class PurchaseAdapter : ListAdapter<Purchase, PurchaseAdapter.PurchaseViewHolder>(PurchaseDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PurchaseViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_purchase, parent, false)
        return PurchaseViewHolder(view)
    }

    override fun onBindViewHolder(holder: PurchaseViewHolder, position: Int) {
        val purchase = getItem(position)
        holder.bind(purchase)
    }

    class PurchaseViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val carNameTextView: TextView = itemView.findViewById(R.id.carNameTextView)
        private val amountTextView: TextView = itemView.findViewById(R.id.amountTextView)
        private val dateTextView: TextView = itemView.findViewById(R.id.dateTextView)
        private val statusTextView: TextView = itemView.findViewById(R.id.statusTextView)

        fun bind(purchase: Purchase) {
            carNameTextView.text = purchase.carName
            amountTextView.text = "$${purchase.price}"
            dateTextView.text = purchase.purchaseDate
            statusTextView.text = purchase.status
        }
    }

    companion object {
        private val PurchaseDiffCallback = object : DiffUtil.ItemCallback<Purchase>() {
            override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
                return oldItem == newItem
            }
        }
    }
}