package com.gabrielFernandes.pokedex.models

import com.google.gson.annotations.SerializedName


data class GenerationV(
    @SerializedName("black-white")
    val blackWhite:BlackWhite
)
