package com.gabrielFernandes.pokedex.models

import com.google.gson.annotations.SerializedName


data class SpritesResponse(
    val sprites: SpritesDetail
)

data class SpritesDetail(
    @SerializedName("generation-viii")
    val generationVIII: GenerationVIII
)

data class GenerationVIII(
    @SerializedName("sword-shield")
    val swordShiel: GameSprite
)

data class GameSprite(
    @SerializedName("name_icon")
    val nameIcon: String
)
