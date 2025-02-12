package com.gabrielFernandes.pokedex.networkRepositories

import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.models.PokemonResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonRepository {

    @GET("pokemon/{id}/")
    suspend fun getOnePokemon(
        @Path("id") id: Int
    ) : Response<Pokemon>

    @GET("pokemon/{name}/")
    suspend fun getOnePokemon(
        @Path("name") name: String
    ) : Response<Pokemon>

    @GET("pokemon")
    suspend fun getPokemons(
        @Query("limit") limit: Int,
        @Query("offset") offset: Int
    ) : Response<PokemonResponse>

}