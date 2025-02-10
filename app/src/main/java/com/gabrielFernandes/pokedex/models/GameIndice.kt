package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GameIndice(
    @SerialName("game_index")
    val gameIndex:Int,
    val version:Version
)
