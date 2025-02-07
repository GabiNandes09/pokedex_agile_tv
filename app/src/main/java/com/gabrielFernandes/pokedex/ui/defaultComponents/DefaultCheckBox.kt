package com.gabrielFernandes.pokedex.ui.defaultComponents

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun DefaultCheckBox(
    text: String,
    onCheckedChange: () -> Unit
) {
    var checkedState by remember { mutableStateOf(false) }

    Row(
        verticalAlignment = Alignment.CenterVertically
    ) {
        Checkbox(
            checked = checkedState,
            onCheckedChange = {
                checkedState = it
                onCheckedChange()
            }
        )
        Text(text = text)
    }
}

@Preview
@Composable
private fun Preview() {
    DefaultCheckBox(
        "Teste",
        {}
    )
}