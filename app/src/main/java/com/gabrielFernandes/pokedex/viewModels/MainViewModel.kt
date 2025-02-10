package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielFernandes.pokedex.models.PokemonResult
import com.gabrielFernandes.pokedex.networkRepositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {
    private val _pokemonsList = MutableStateFlow<List<PokemonResult>>(emptyList())
    val pokemonsList = _pokemonsList.asStateFlow()

    private val _imagesList = MutableStateFlow<List<String?>>(emptyList())
    val imageList = _imagesList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokemonsWithImages(50, 0)
        }
    }

    private suspend fun loadPokemonsWithImages(limit: Int, offset: Int) {
        val pkResponse = pokemonRepository.getPokemons(limit, offset).body()
        val pokemons = pkResponse?.results ?: emptyList()

        val images = pokemons.map { pk ->
            pokemonRepository.getOnePokemon(pokemons.indexOf(pk) + 1).body()?.sprites?.frontDefault
        }

        _pokemonsList.value = pokemons
        _imagesList.value = images
    }
}