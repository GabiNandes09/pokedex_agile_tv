package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class VersionGroup(
    val name: String,
    val url: String
)