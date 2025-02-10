package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class Form(
    val name: String,
    val url: String
)