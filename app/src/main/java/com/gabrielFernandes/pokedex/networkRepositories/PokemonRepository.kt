package com.gabrielFernandes.pokedex.networkRepositories

import retrofit2.http.GET
import retrofit2.http.Path

interface PokemonRepository {

    @GET("pokemon/{id}")
    suspend fun getOnePokemon(
        @Path("id") id: Int
    )

    @GET("pokemon?limit={limit}&offset={offset}")
    suspend fun getPokemons(
        @Path("limit") limit: Int,
        @Path("offset") offset: Int
    )
}