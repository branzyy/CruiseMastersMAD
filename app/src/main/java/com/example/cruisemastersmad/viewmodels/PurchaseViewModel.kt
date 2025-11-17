package com.example.cruisemastersmad.viewmodels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.cruisemastersmad.database.AppDatabase
import com.example.cruisemastersmad.models.Purchase
import kotlinx.coroutines.launch

class PurchaseViewModel(application: Application) : AndroidViewModel(application) {
    private val purchaseDao = AppDatabase.getDatabase(application).purchaseDao()

    private val _purchases = MutableLiveData<List<Purchase>>()
    val purchases: LiveData<List<Purchase>> get() = _purchases

    fun loadPurchases(email: String) {
        viewModelScope.launch {
            val purchaseList = purchaseDao.getPurchasesByEmail(email)
            _purchases.postValue(purchaseList)
        }
    }

    fun purchaseCar(purchase: Purchase, onResult: (Long) -> Unit) {
        viewModelScope.launch {
            val id = purchaseDao.insertPurchase(purchase)
            onResult(id)
        }
    }
}
