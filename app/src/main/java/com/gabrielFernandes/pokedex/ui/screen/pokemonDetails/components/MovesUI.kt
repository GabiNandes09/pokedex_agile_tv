package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.PressInteraction
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.gabrielFernandes.pokedex.models.Pokemon
import kotlinx.coroutines.flow.collectLatest

@Composable
fun MovesUI(
    pokemon: Pokemon?
) {
    var expanded by remember { mutableStateOf(false) }
    Column(
        modifier = Modifier
            .padding(10.dp)
            .fillMaxWidth()
    ) {
        TextField(
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color.LightGray,
                disabledContainerColor = Color.LightGray,
                unfocusedContainerColor = Color.LightGray,
                errorContainerColor = Color.LightGray,
                focusedTextColor = Color.Black,
                unfocusedTextColor = Color.Black
            ),
            modifier = Modifier.fillMaxWidth(),
            value = "Moves",
            onValueChange = {},
            trailingIcon = {
                Icon(
                    imageVector = if (expanded)
                        Icons.Default.KeyboardArrowUp
                    else
                        Icons.Default.KeyboardArrowDown,
                    contentDescription = null,
                    tint = Color.Black
                )
            },
            readOnly = true,
            interactionSource = remember {
                MutableInteractionSource()
            }.also {
                LaunchedEffect(key1 = it) {
                    it.interactions.collectLatest { interaction ->
                        if (interaction is PressInteraction.Release) {
                            expanded = !expanded
                        }
                    }
                }
            }
        )
        AnimatedVisibility(visible = expanded) {
            LazyColumn(
                modifier = Modifier.fillMaxWidth().heightIn(max = 300.dp)
            ) {
                items(pokemon?.moves!!){move ->
                    ItemMoves(move = formatMove(move.move.name))
                }
            }
        }
    }
}

private fun formatMove(move: String) : String{
    return move
        .replaceFirstChar { it.uppercaseChar() }
        .replace("-", " ")

}

@Composable
private fun ItemMoves(
    move: String
) {
    TextField(
        modifier = Modifier
            .padding(vertical = 2.5.dp, horizontal = 10.dp)
            .fillMaxWidth(),
        value = move,
        onValueChange = {},
        readOnly = true
    )
}

