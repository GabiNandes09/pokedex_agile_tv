package com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.IconButtonDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil3.compose.AsyncImage
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.isPortrait
import com.gabrielFernandes.pokedex.viewModels.PokemonDetailViewModel
import org.koin.androidx.compose.koinViewModel

@Composable
fun FloatButtonsUI(id: Int) {

    val viewModel: PokemonDetailViewModel = koinViewModel()

    val pokemonAtual by viewModel.pokemon.collectAsState()
    val nextPk by viewModel.next.collectAsState()
    val beforePk by viewModel.before.collectAsState()

    var atual by remember {
        mutableIntStateOf(id)
    }

    LaunchedEffect(atual) {
        viewModel.nextOrBeforePokemon(atual)
    }

    if (isPortrait()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (atual > 1) {
                IconButton(
                    onClick = { atual-- },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .border(2.dp, Color.Black, CircleShape)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }
                Box( // Anterior
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(2.dp, Color.Black, CircleShape)
                        .size(50.dp)
                ) {
                    AsyncImage(
                        model = beforePk?.sprites?.frontDefault,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.Black, CircleShape)
                    .size(70.dp)
            ) {
                AsyncImage(
                    model = pokemonAtual?.sprites?.frontDefault,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.Black, CircleShape)
                    .size(50.dp)
            ) {
                AsyncImage(
                    model = nextPk?.sprites?.frontDefault,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(
                onClick = { atual++ },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .border(2.dp, Color.Black, CircleShape)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null,
                    Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        }
    } else {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (atual > 1) {
                IconButton(
                    onClick = { atual-- },
                    colors = IconButtonDefaults.iconButtonColors(
                        containerColor = Color.White
                    ),
                    modifier = Modifier
                        .border(2.dp, Color.Black, CircleShape)
                        .weight(1f)
                ) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                        contentDescription = null,
                        Modifier.size(40.dp),
                        tint = Color.Black
                    )
                }
                Box( // Anterior
                    modifier = Modifier
                        .padding(start = 15.dp)
                        .clip(CircleShape)
                        .background(Color.White)
                        .border(2.dp, Color.Black, CircleShape)
                        .size(50.dp)
                ) {
                    AsyncImage(
                        model = beforePk?.sprites?.frontDefault,
                        contentDescription = null,
                        modifier = Modifier.fillMaxSize()
                    )
                }
            } else {
                Spacer(modifier = Modifier.weight(1f))
            }

            Box(
                modifier = Modifier
                    .padding(horizontal = 5.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.Black, CircleShape)
                    .size(70.dp)
            ) {
                AsyncImage(
                    model = pokemonAtual?.sprites?.frontDefault,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            Box(
                modifier = Modifier
                    .padding(end = 15.dp)
                    .clip(CircleShape)
                    .background(Color.White)
                    .border(2.dp, Color.Black, CircleShape)
                    .size(50.dp)
            ) {
                AsyncImage(
                    model = nextPk?.sprites?.frontDefault,
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
            IconButton(
                onClick = { atual++ },
                colors = IconButtonDefaults.iconButtonColors(
                    containerColor = Color.White
                ),
                modifier = Modifier
                    .border(2.dp, Color.Black, CircleShape)
                    .weight(1f)
            ) {
                Icon(
                    imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                    contentDescription = null,
                    Modifier.size(40.dp),
                    tint = Color.Black
                )
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    FloatButtonsUI(5)
}