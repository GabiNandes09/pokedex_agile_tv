package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.networkRepositories.PokemonRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {


    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    private val pokemons = _pokemons.asStateFlow()

    init {

    }

   suspend fun loadPokemons(){
        pokemonRepository.getPokemons(150, 0)
    }
}