package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun NameAndNumber(
    name: String,
    number: Int
) {
    Row(
        modifier = Modifier
            .padding(top = 30.dp)
            .width(300.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
            .padding(20.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Text(
            text = name.replaceFirstChar { it.uppercaseChar() }.replace("-", "\n"),
            fontSize = 30.sp,
            modifier = Modifier.weight(1f),
            color = Color.Black
        )
        Text(
            text = "#$number",
            fontSize = 30.sp,
            color = Color.Black
        )
    }
}