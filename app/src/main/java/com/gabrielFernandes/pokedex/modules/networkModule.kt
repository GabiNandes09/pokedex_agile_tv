package com.gabrielFernandes.pokedex.modules

import com.gabrielFernandes.pokedex.networkRepositories.PokemonRepository
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

const val BASE_URL = "https://pokeapi.co/api/v2/"

val networkModule = module {
    single {
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
    single { get<Retrofit>().create(PokemonRepository::class.java) }
}