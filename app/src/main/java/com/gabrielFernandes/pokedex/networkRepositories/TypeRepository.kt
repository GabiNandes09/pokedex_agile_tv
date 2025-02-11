package com.gabrielFernandes.pokedex.networkRepositories

import com.gabrielFernandes.pokedex.models.SpritesResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface TypeRepository {

    @GET("type/{type}/")
    suspend fun getOneType(
        @Path("type") name: String) : Response<SpritesResponse>
}