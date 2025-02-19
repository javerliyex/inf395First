package com.jax.inf395first.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jax.inf395first.presentation.ItemViewModel

@Composable
fun SearchScreen(viewModel: ItemViewModel, navController: NavController) {
    var query by remember { mutableStateOf("") }
    val items by viewModel.items.collectAsState()
    val filteredItems = items.filter {
        it["itemName"]?.toString()?.contains(query, ignoreCase = true) == true ||
                it["category"]?.toString()?.contains(query, ignoreCase = true) == true ||
                it["locationFound"]?.toString()?.contains(query, ignoreCase = true) == true
    }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Search for a Lost Item", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        TextField(value = query, onValueChange = { query = it }, label = { Text("Search by name, category, or location") })

        LazyColumn {
            items(filteredItems) { item ->
                Text(text = "${item["itemName"]} - ${item["locationFound"]}")
            }
        }

        Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back")
        }
    }
}

