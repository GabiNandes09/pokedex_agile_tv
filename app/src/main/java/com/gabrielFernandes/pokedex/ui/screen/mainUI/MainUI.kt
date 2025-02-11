package com.gabrielFernandes.pokedex.ui.screen.mainUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.gabrielFernandes.pokedex.ui.screen.mainUI.components.BackGroundMain
import com.gabrielFernandes.pokedex.ui.screen.mainUI.components.ListAll
import com.gabrielFernandes.pokedex.ui.screen.mainUI.components.SearchAndFilterBar
import com.gabrielFernandes.pokedex.viewModels.MainViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun MainUI(navController: NavController) {
    val viewModel: MainViewModel = koinViewModel()
    val pkList by viewModel.pokemonsList.collectAsState()

    Scaffold { paddingValues ->
        BackGroundMain()
        Column(
            modifier = Modifier.padding(paddingValues)
        ) {
            SearchAndFilterBar(
                Modifier.padding(vertical = 20.dp, horizontal = 10.dp)
            )
            ListAll(
                pkList
            ) { id -> navController.navigate("pokemon/$id") }
        }
    }
}

@Preview
@Composable
private fun Prev() {
    val navController = rememberNavController()
    MainUI(navController)
}