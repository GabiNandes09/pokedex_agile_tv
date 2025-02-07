package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class Type(
    val name: String,
    val slot: Int
)
