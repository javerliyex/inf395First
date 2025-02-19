package com.jax.inf395first.presentation.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
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
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReportItemScreen(viewModel: ItemViewModel, navController: NavController) {
    var itemName by remember { mutableStateOf("") }
    var category by remember { mutableStateOf("") }
    var locationFound by remember { mutableStateOf("") }

    Column(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        Text(text = "Report a Found Item", fontSize = 20.sp, fontWeight = FontWeight.Bold)

        TextField(value = itemName, onValueChange = { itemName = it }, label = { Text("Item Name") })
        TextField(value = category, onValueChange = { category = it }, label = { Text("Category") })
        TextField(value = locationFound, onValueChange = { locationFound = it }, label = { Text("Location Found") })

        Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {
            Button(onClick = { navController.popBackStack() }) {
                Text("Back")
            }
            Button(onClick = {
                if (itemName.isNotBlank() && category.isNotBlank() && locationFound.isNotBlank()) {
                    viewModel.addItem(
                        itemName, category, locationFound,
                        SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())
                    )
                    navController.popBackStack()
                }
            }) {
                Text("Submit")
            }
        }
    }
}

