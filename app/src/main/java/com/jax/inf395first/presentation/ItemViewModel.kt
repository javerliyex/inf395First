package com.jax.inf395first.presentation

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class ItemViewModel : ViewModel() {

    private val _items = MutableStateFlow<MutableList<MutableMap<String, Any>>>(mutableListOf())
    val items: StateFlow<List<Map<String, Any>>> = _items.asStateFlow()

    fun addItem(itemName: String, category: String, locationFound: String, dateFound: String) {
        val newItem: MutableMap<String, Any> = mutableMapOf(
            "id" to (_items.value.size + 1),
            "itemName" to itemName,
            "category" to category,
            "locationFound" to locationFound,
            "dateFound" to dateFound,
            "claimedStatus" to false
        )
        _items.value = (_items.value + newItem).toMutableList()
    }

    fun searchItems(query: String): List<Map<String, Any>> {
        return _items.value.filter {
            it["itemName"]?.toString()?.contains(query, ignoreCase = true) == true ||
                    it["category"]?.toString()?.contains(query, ignoreCase = true) == true ||
                    it["locationFound"]?.toString()?.contains(query, ignoreCase = true) == true
        }
    }

    fun getUnclaimedItems(): List<Map<String, Any>> {
        return _items.value.filter { it["claimedStatus"] == false }
    }

    fun claimItem(id: Int) {
        val updatedList = _items.value.map { item ->
            if (item["id"] == id) {
                item.toMutableMap().apply { this["claimedStatus"] = true }
            } else {
                item
            }
        }.toMutableList()
        _items.value = updatedList
    }
}

