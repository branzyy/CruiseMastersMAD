package com.example.cruisemastersmad.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.cruisemastersmad.databinding.ActivityPurchaseBinding
import com.example.cruisemastersmad.adapters.PurchaseAdapter
import com.example.cruisemastersmad.models.Purchase

class PurchaseActivity : AppCompatActivity() {
    private lateinit var binding: ActivityPurchaseBinding
    private lateinit var purchaseAdapter: PurchaseAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPurchaseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupRecyclerView()
        setupClickListeners()
    }

    private fun setupRecyclerView() {
        purchaseAdapter = PurchaseAdapter()

        binding.purchasesRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@PurchaseActivity)
            adapter = purchaseAdapter
        }

        purchaseAdapter.submitList(getSamplePurchases())
    }

    private fun setupClickListeners() {
        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private fun getSamplePurchases(): List<Purchase> {
        return listOf(
            Purchase(
                purchaseID = "1",
                vehiclename = "BMW M8",
                purchasedate = "2024-01-15",
                email = "user@example.com",
                status = "Completed"
            ),
            Purchase(
                purchaseID = "2",
                vehiclename = "Mercedes AMG",
                purchasedate = "2024-02-01",
                email = "user@example.com",
                status = "Processing"
            )
        )
    }
}
