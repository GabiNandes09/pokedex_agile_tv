package com.gabrielFernandes.pokedex.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Popup
import com.gabrielFernandes.pokedex.models.PokemonResult

@Composable
fun ListAll(pkList: List<PokemonResult>) {
    LazyVerticalGrid(columns = GridCells.Fixed(3)) {
        pkList.forEach{pokemon ->
            item {
                ItemListAll(
                    index = pkList.indexOf(pokemon),
                    name = pokemon.name
                )
            }
        }
    }
}

@Composable
private fun ItemListAll(
    index: Int,
    name: String
) {
    val textColor = Color.Black
    var showDialog by remember {
        mutableStateOf(false)
    }
    if (showDialog){
        Popup(
            alignment = Alignment.Center,
        ) {
            PokemonPreview()
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
                text = index.toString(),
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(10.dp),
                color = textColor
            )
            Spacer(modifier = Modifier
                .size(80.dp)
                .background(Color.Blue))
            Text(
                text = name,
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
    ListAll(emptyList())
}