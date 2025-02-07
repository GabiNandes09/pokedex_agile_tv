package com.gabrielFernandes.pokedex.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun BackGroundMain() {

    Column {
        Spacer(
            modifier = Modifier
                .background(Color.Red)
                .fillMaxSize()
                .weight(1f)
        )
        Spacer(
            modifier = Modifier
                .background(Color.White)
                .fillMaxSize()
                .weight(1f)
        )
    }
    Box(
        modifier = Modifier
            .background(Color.Transparent)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Spacer(
            modifier = Modifier
                .background(Color.Black)
                .fillMaxWidth()
                .height(35.dp)
        )
        Spacer(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.Black)
                .size(175.dp)
        )
        Spacer(
            modifier = Modifier
                .clip(CircleShape)
                .background(Color.White)
                .size(120.dp)
        )
    }
}

@Preview
@Composable
private fun Preview() {
    BackGroundMain()
}