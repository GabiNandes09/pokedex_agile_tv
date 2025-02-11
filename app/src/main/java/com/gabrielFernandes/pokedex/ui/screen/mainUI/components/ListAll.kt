package com.gabrielFernandes.pokedex.ui.screen.mainUI.components

import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import coil3.compose.AsyncImage
import com.gabrielFernandes.pokedex.R
import com.gabrielFernandes.pokedex.models.Pokemon

@Composable
fun ListAll(
    pkList: List<Pokemon?>
) {
    if (pkList.isEmpty()) {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            for(i in 1..50) {
                item {
                    ItemListAllPreview()
                }
            }
        }
    } else {
        LazyVerticalGrid(columns = GridCells.Fixed(3)) {
            pkList.forEach { pokemon ->
                item {
                    ItemListAll(
                        pokemon
                    )
                }
            }
        }
    }
}

@Composable
private fun ItemListAllPreview() {
    val infiniteTransition = rememberInfiniteTransition(label = "infiniteTransition")
    val localConfig = LocalConfiguration.current
    val target = (localConfig.screenWidthDp * 3).toFloat()

    val scale by infiniteTransition.animateFloat(
        initialValue = 0f,
        targetValue = target,
        animationSpec = infiniteRepeatable(
            animation = tween(1000)
        ), label = "Shrimmer"
    )
    val shrimmer = Brush.linearGradient(
        colors = listOf(
            Color.Gray.copy(alpha = 0.5f),
            Color.Gray.copy(alpha = 0.1f),
            Color.Gray.copy(alpha = 0.5f),
        ),
        end = Offset(x = scale, y = scale)
    )

    Box(
        modifier = Modifier
            .padding(5.dp)
            .width(100.dp)
            .height(170.dp)
            .background(shrimmer),
    ) {

    }
}

@Composable
private fun ItemListAll(
    pokemon: Pokemon?
) {

    val textColor = Color.Black
    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog && pokemon != null) {
        Popup(
            alignment = Alignment.Center,
        ) {
            PokemonPreview(
                pokemon
            )
        }
    }
    Card(
        modifier = Modifier
            .padding(5.dp)
            .width(100.dp)
            .height(170.dp)
            .pointerInput(Unit) {
                detectTapGestures(
                    onLongPress = {
                        showDialog = true
                    },
                    onPress = {
                        tryAwaitRelease()
                        showDialog = false
                    }
                )
            },
        colors = CardDefaults.cardColors(
            containerColor = Color.LightGray.copy(alpha = .75f)
        )
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "#${pokemon?.id}",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = textColor
            )
            AsyncImage(
                model = pokemon?.sprites?.frontDefault,
                contentDescription = null,
                modifier = Modifier.size(80.dp),
                placeholder = painterResource(id = R.drawable.no_image_foreground)
            )
            Text(
                text = pokemon?.name ?: "",
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = textColor
            )
        }
    }
}

@Preview
@Composable
private fun Preview() {
    ListAll(
        emptyList()
    )
}