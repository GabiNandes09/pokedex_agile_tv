package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class Species(
    val name: String,
    val url: String
)