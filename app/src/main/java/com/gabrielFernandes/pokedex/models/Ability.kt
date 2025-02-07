package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class Ability(
    val name: String
) {
    override fun toString(): String {
        return name
    }
}