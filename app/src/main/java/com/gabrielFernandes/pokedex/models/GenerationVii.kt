package com.gabrielFernandes.pokedex.models

import com.google.gson.annotations.SerializedName


data class GenerationVii(
    val icons:Icons,
    @SerializedName("ultra-sun-ultra-moon")
    val ultraSunUltraMoon:UltraSunUltraMoon
)
