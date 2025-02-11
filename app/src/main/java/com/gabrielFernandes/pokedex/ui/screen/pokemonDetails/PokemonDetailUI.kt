package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.gabrielFernandes.pokedex.ui.screen.mainUI.components.BackGroundMain
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.BasicStats
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.NameAndNumber
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components.PhotoPokemon
import com.gabrielFernandes.pokedex.viewModels.PokemonDetailViewModel
import org.koin.compose.koinInject

@Composable
fun PokemonDetailUI(
    id: Int
) {
    val viewModel: PokemonDetailViewModel = koinInject()
    val pokemon by viewModel.pokemon.collectAsState()
    val types by viewModel.types.collectAsState()
    val stats by viewModel.stats.collectAsState()

    LaunchedEffect(id) {
        viewModel.loadPokemon(id)
    }

    BackGroundMain()
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier.fillMaxSize()
    ) {
        NameAndNumber(
            name = pokemon?.name ?: "",
            number = pokemon?.id ?: 0
        )

        PhotoPokemon(
            urlImage = pokemon?.sprites?.frontDefault
        )
        BasicStats(
            types = types,
            stats = stats
        )
    }
}


@Preview
@Composable
private fun Preview() {
    PokemonDetailUI(id = 0)
}

