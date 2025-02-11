package com.gabrielFernandes.pokedex.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil3.compose.AsyncImage
import com.gabrielFernandes.pokedex.R
import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.viewModels.PokemonPreviewViewModel
import org.koin.compose.koinInject

@Composable
fun PokemonPreview(
    pokemon: Pokemon
) {
    val viewModel: PokemonPreviewViewModel = koinInject()
    val typeList by viewModel.typeList.collectAsState()

    val textColor = Color.Black

    LaunchedEffect(pokemon) {
        viewModel.loadTypes(pokemon)
    }

    Column(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .shadow(5.dp, RoundedCornerShape(20.dp))
            .background(Color.LightGray)
            .border(3.dp, Color.Gray, RoundedCornerShape(20.dp)),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Row(
            modifier = Modifier
                .width(350.dp)
                .padding(10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = pokemon.name,
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp),
                color = textColor
            )
            Text(
                text = "Nº ${pokemon.id}",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(5.dp),
                color = textColor
            )
        }
        AsyncImage(
            model = pokemon.sprites.frontDefault,
            contentDescription = null,
            modifier = Modifier.size(250.dp)
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
            typeList.forEach { type ->
                AsyncImage(
                    model = type,
                    contentDescription = null,
                    modifier = Modifier
                        .padding(end = 10.dp)
                        .width(80.dp)
                        .height(35.dp),
                    placeholder = painterResource(id = R.drawable.no_image_foreground)
                )
            }
        }
    }
}

