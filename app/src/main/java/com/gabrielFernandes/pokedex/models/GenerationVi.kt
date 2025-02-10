package com.gabrielFernandes.pokedex.models

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class GenerationVi(
    @SerialName("omegaruby-alphasapphire")
    val omegarubyAlphasapphire:OmegarubyAlphasapphire,
    @SerialName("x-y")
    val xy:XY
)
