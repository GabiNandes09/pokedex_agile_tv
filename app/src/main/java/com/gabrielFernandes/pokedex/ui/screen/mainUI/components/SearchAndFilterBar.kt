package com.gabrielFernandes.pokedex.ui.screen.mainUI.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.gabrielFernandes.pokedex.R

@Composable
fun SearchAndFilterBar(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
    ) {
        Row(
            modifier = Modifier
                .clip(RoundedCornerShape(15.dp))
                .fillMaxWidth()
                .background(Color.White),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                value = "",
                onValueChange = {},
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search, contentDescription = stringResource(
                            id = R.string.search_ico_description
                        )
                    )
                },
                colors = TextFieldDefaults.colors(
                    disabledIndicatorColor = Color.Transparent,
                    focusedIndicatorColor = Color.Transparent,
                    errorIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent
                )
            )
            IconButton(
                onClick = { /*TODO*/ },
                modifier = Modifier.padding(5.dp)
            ) {
                Icon(
                    painterResource(id = R.drawable.filter_ico),
                    contentDescription = stringResource(id = R.string.filter_ico_description)
                )
            }
        }
    }

}

@Preview
@Composable
private fun Preview() {
    SearchAndFilterBar()
}