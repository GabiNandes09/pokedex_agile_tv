package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class StatX(
    val name: String,
    val url: String
)