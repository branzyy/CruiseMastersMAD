package com.example.cruisemastersmad.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.cruisemastersmad.R
import com.example.cruisemastersmad.models.Purchase

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
        private val carNameTextView: TextView = itemView.findViewById(R.id.purchaseCarName)
        private val amountTextView: TextView = itemView.findViewById(R.id.purchaseAmount)
        private val dateTextView: TextView = itemView.findViewById(R.id.purchaseDate)
        private val statusTextView: TextView = itemView.findViewById(R.id.purchaseStatus)

        fun bind(purchase: Purchase) {
            carNameTextView.text = purchase.vehiclename
            amountTextView.text = "$${purchase.purchaseID}"
            dateTextView.text = purchase.purchasedate
            statusTextView.text = purchase.status
        }
    }

    companion object {
        private val PurchaseDiffCallback = object : DiffUtil.ItemCallback<Purchase>() {
            override fun areItemsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
                return oldItem.purchaseID == newItem.purchaseID
            }

            override fun areContentsTheSame(oldItem: Purchase, newItem: Purchase): Boolean {
                return oldItem == newItem
            }
        }
    }
}