package com.gabrielFernandes.pokedex.models


data class PokemonResponse(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: List<PokemonResult>
)


data class PokemonResult(
    val name: String,
    val url: String
)
