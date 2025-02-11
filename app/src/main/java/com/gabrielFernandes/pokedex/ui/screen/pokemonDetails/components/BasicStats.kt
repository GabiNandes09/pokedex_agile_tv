package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage

@Composable
fun BasicStats(
    types: List<String>,
    stats: Map<String, Int>
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
        modifier = Modifier
            .padding(horizontal = 15.dp)
            .clip(RoundedCornerShape(20.dp))
            .background(Color.White)
            .border(2.dp, Color.Black, RoundedCornerShape(20.dp))
            .padding(20.dp)
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Type: ",
                fontSize = 20.sp
            )
            types.forEach { type ->
                AsyncImage(
                    model = type,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .background(Color.LightGray)
                        .width(80.dp)
                )
            }
        }
        stats.forEach { key ->
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    horizontalAlignment = Alignment.End,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    Text(text = formartStats(key.key) + ": ")
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                ) {
                    Text(text = key.value.toString())
                }
                Column(
                    horizontalAlignment = Alignment.Start,
                    modifier = Modifier
                        .fillMaxWidth()
                        .weight(1f)
                ) {
                    LinearProgressIndicator(
                        progress = { (key.value.toFloat() / 255f) },
                        modifier = Modifier
                            .padding(start = 5.dp)
                            .fillMaxWidth()
                            .height(8.dp)
                            .clip(RoundedCornerShape(4.dp)),
                        color = Color.Red,
                        trackColor = Color.Black,
                        gapSize = 0.dp
                    )
                }
            }
        }
    }
}

private fun formartStats(stat: String): String {
    return stat
        .replaceFirstChar { it.uppercaseChar() }
        .replace("-", " ")
}