package com.jax.inf395first.presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jax.inf395first.presentation.screens.ReportItemScreen
import com.jax.inf395first.presentation.screens.SearchScreen
import com.jax.inf395first.presentation.screens.UnclaimedItemsScreen

@Composable
fun MainScreen(navController: NavController, viewModel: ItemViewModel) {
    val items by viewModel.items.collectAsState()
    val unclaimedCount = items.count { it["claimedStatus"] == false }

    Column(modifier = Modifier
        .fillMaxSize()
        .padding(16.dp), verticalArrangement = Arrangement.Center) {
        Text(text = "Sufyan teacher your best")
        Text(text = "Lost & Found System", fontSize = 24.sp, fontWeight = FontWeight.Bold, modifier = Modifier.padding(bottom = 16.dp))

        Button(onClick = { navController.navigate("report") }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            Text("Report a Found Item")
        }
        Button(onClick = { navController.navigate("search") }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            Text("Search for a Lost Item")
        }
        Button(onClick = { navController.navigate("unclaimed") }, modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp)) {
            Text("View Unclaimed Items ($unclaimedCount)")
        }
    }
}

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val viewModel = remember { ItemViewModel() }

    NavHost(navController, startDestination = "menu") {
        composable("menu") { MainScreen(navController, viewModel) }
        composable("report") { ReportItemScreen(viewModel, navController) }
        composable("search") { SearchScreen(viewModel, navController) }
        composable("unclaimed") { UnclaimedItemsScreen(viewModel, navController) }
    }
}

