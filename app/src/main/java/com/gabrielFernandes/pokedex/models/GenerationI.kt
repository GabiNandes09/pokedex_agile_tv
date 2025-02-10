package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationI(
    @SerialName("red-blue")
    val redBlue:RedBlue,
    val yellow:Yellow
)
