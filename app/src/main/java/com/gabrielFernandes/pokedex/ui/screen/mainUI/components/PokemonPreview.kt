package com.gabrielFernandes.pokedex.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PokemonPreview(modifier: Modifier = Modifier) {

    val textColor = Color.Black
    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .shadow(5.dp, RoundedCornerShape(20.dp))
            .background(Color.LightGray)
            .border(3.dp, Color.Gray, RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier
                .width(350.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = "Bulbassar",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp),
                color = textColor
            )
            Text(
                text = "Nº 0001",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp),
                color = textColor
            )
        }
        Spacer(
            modifier = Modifier
                .size(250.dp)
                .background(Color.Blue)
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(
                text = "Type: ",
                color = textColor,
                modifier = Modifier.padding(end = 10.dp)
            )
            Spacer(
                modifier = Modifier
                    .padding(end = 10.dp)
                    .clip(CircleShape)
                    .background(Color.Red)
                    .size(35.dp)
            )
            Spacer(
                modifier = Modifier
                    .clip(CircleShape)
                    .background(Color.Green)
                    .size(35.dp)
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    PokemonPreview()
}