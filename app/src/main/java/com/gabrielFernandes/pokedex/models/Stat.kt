package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Stat(
    val name: String,
    @SerialName("base_stat")
    val baseStat: Int
)
