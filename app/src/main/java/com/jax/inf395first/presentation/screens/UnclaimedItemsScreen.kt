package com.jax.inf395first.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.jax.inf395first.presentation.ItemViewModel

@Composable
fun UnclaimedItemsScreen(viewModel: ItemViewModel, navController: NavController) {
    val items by viewModel.items.collectAsState()
    val unclaimedItems = items.filter { it["claimedStatus"] == false }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Unclaimed Items", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        LazyColumn {
            items(unclaimedItems) { item ->
                Card(modifier = Modifier.fillMaxWidth().padding(8.dp)) {
                    Row(modifier = Modifier.padding(8.dp), verticalAlignment = Alignment.CenterVertically) {
                        Column(modifier = Modifier.weight(1f)) {
                            Text(text = item["itemName"].toString(), fontWeight = FontWeight.Bold)
                            Text(text = "Category: ${item["category"]}")
                            Text(text = "Location: ${item["locationFound"]}")
                            Text(text = "Date Found: ${item["dateFound"]}")
                        }
                        Button(onClick = { viewModel.claimItem(item["id"] as Int) }) {
                            Text("Claim")
                        }
                    }
                }
            }
        }

        Button(onClick = { navController.popBackStack() }, modifier = Modifier.padding(top = 16.dp)) {
            Text("Back")
        }
    }
}


