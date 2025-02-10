package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationIv(
    @SerialName("diamond-pearl")
    val diamondPearl:DiamondPearl,
    @SerialName("heartgold-soulsilver")
    val heartgoldSoulsilver:HeartgoldSoulsilver,
    val platinum:Platinum
)
