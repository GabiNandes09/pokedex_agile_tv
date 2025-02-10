package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val ability: AbilityX,
    @SerialName("is_hidden")
    val isHidden: Boolean,
    val slot: Int
)