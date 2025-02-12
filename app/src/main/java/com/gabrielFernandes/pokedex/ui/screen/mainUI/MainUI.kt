package com.gabrielFernandes.pokedex.ui.screen.mainUI

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
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
    val isConnected by viewModel.network.isConnected.collectAsState()

    Scaffold { paddingValues ->
        BackGroundMain()
        if (isConnected) {
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                SearchAndFilterBar(
                    modifier = Modifier.padding(vertical = 20.dp, horizontal = 10.dp),
                    onValueChange = { filter -> viewModel.filterPokemons(filter) }
                )
                ListAll(
                    onclick = { id -> navController.navigate("pokemon/$id") },
                    loadMore = { viewModel.loadMorePokemons() }
                )
            }
        } else {
            Column(
                modifier = Modifier.padding(paddingValues)
            ) {
                AlertDialog(
                    onDismissRequest = {
                        navController.popBackStack()
                        navController.navigate("main")
                    },
                    confirmButton = { /*TODO*/ },
                    title = { Text(text = "Sem conexão") },
                    text = { Text(text = "Este aplicativo necessita de conexão com a internet") })
            }
        }

    }
}

@Preview
@Composable
private fun Prev() {
    val navController = rememberNavController()
    MainUI(navController)
}