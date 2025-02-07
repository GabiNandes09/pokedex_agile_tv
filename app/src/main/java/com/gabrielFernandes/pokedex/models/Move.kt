package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.Serializable

@Serializable
data class Move(
    val name: String
){
    override fun toString(): String {
        return name
    }
}
