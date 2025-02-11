package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.networkRepositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _pokemonsList = MutableStateFlow<List<Pokemon?>>(emptyList())
    val pokemonsList = _pokemonsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokemonsWithImages(20, 0)
        }
    }

    private suspend fun loadPokemonsWithImages(limit: Int, offset: Int) {
        val pkResponse = pokemonRepository.getPokemons(limit, offset).body()
        val pokemons = pkResponse?.results ?: emptyList()

        val pokemonsList = pokemons.map { pk ->
            pokemonRepository.getOnePokemon(pokemons.indexOf(pk) + 1).body()
        }

        _pokemonsList.value = pokemonsList
    }


}