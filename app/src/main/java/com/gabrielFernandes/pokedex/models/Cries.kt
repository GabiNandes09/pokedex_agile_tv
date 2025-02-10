package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class Cries(
    val latest: String,
    val legacy: String
)