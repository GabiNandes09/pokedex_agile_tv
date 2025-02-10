package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)

@Serializable
data class PokemonResult(
    val name: String,
    val url: String
)
