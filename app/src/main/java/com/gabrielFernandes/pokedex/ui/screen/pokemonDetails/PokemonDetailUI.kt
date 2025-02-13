package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.FabPosition
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielFernandes.pokedex.ui.screen.mainUI.components.BackGroundMain
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.AbilitysUI
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.BasicStats
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.FloatButtonsUI
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.MovesUI
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.NameAndNumber
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.PhotoPokemon
import com.gabrielFernandes.pokedex.viewModels.PokemonDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun PokemonDetailUI(
    id: Int
) {
    val viewModel: PokemonDetailViewModel = koinViewModel()
    val pokemon by viewModel.pokemon.collectAsState()
    val types by viewModel.types.collectAsState()
    val stats by viewModel.stats.collectAsState()
    val isConnected by viewModel.networkMonitor.isConnected.collectAsState()

    if (isConnected){
        LaunchedEffect(id) {
            viewModel.loadPokemon(id)
        }
        LaunchedEffect(pokemon) {
            pokemon?.id?.let { viewModel.loadPokemon(it) }
        }

        if (isPortrait()) {
            Scaffold(
                floatingActionButton = { FloatButtonsUI(id = id) },
                floatingActionButtonPosition = FabPosition.Center
            ) { paddingValues ->
                BackGroundMain()
                LazyColumn(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(paddingValues)
                        .fillMaxSize()
                ) {
                    item {
                        NameAndNumber(
                            name = pokemon?.name ?: "",
                            number = pokemon?.id ?: 0
                        )
                    }

                    item {
                        PhotoPokemon(
                            urlImage = pokemon?.sprites?.frontDefault
                        )
                    }

                    item {
                        BasicStats(
                            types = types,
                            stats = stats
                        )
                    }
                    item {
                        AbilitysUI(
                            pokemon
                        )
                    }
                    item {
                        MovesUI(pokemon = pokemon)
                    }
                    item {
                        Spacer(modifier = Modifier.height(100.dp))
                    }
                }
            }
        }
        else {
            Scaffold(
                floatingActionButton = { FloatButtonsUI(id = id) },
                floatingActionButtonPosition = FabPosition.Center
            ) { paddingValues ->
                BackGroundMain()
                Row {
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        NameAndNumber(
                            name = pokemon?.name ?: "",
                            number = pokemon?.id ?: 0
                        )
                        PhotoPokemon(
                            urlImage = pokemon?.sprites?.frontDefault
                        )
                    }
                    LazyColumn(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        modifier = Modifier
                            .padding(paddingValues)
                            .fillMaxSize()
                    ) {
                        item {
                            BasicStats(
                                types = types,
                                stats = stats
                            )
                        }
                        item {
                            AbilitysUI(
                                pokemon
                            )
                        }
                        item {
                            MovesUI(pokemon = pokemon)
                        }
                        item {
                            Spacer(modifier = Modifier.height(100.dp))
                        }
                    }
                }
            }
        }
    }
    else {
        Scaffold { paddingValues ->
            BackGroundMain()
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(paddingValues)
                    .fillMaxSize()
            ) {
                AlertDialog(
                    onDismissRequest = { /*TODO*/ },
                    confirmButton = { /*TODO*/ },
                    title = { Text(text = "Sem conexão")},
                    text = { Text(text = "Este aplicativo necessita de uma conexão com a internet")}
                )
            }
        }
    }
    
}

@Composable
fun isPortrait(): Boolean {
    val configuration = LocalConfiguration.current
    return configuration.orientation == Configuration.ORIENTATION_PORTRAIT
}


@Preview
@Composable
private fun Preview() {
    PokemonDetailUI(id = 0)
}

