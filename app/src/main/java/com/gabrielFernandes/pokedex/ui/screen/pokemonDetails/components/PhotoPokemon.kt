package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage

@Composable
fun PhotoPokemon(
    urlImage: String?
) {
    Card(
        modifier = Modifier
            .padding(20.dp)
            .size(300.dp),
        colors = CardDefaults.cardColors(
            containerColor = Color.Gray.copy(alpha = 0.5f)
        )
    ) {
        AsyncImage(
            model = urlImage,
            contentDescription = null,
            modifier = Modifier.fillMaxSize()
        )
    }
}