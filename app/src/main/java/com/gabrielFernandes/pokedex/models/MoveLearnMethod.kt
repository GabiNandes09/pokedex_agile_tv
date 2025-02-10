package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class MoveLearnMethod(
    val name: String,
    val url: String
)