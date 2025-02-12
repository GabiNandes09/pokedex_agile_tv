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

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loadingMore = MutableStateFlow(false)
    val loadingMore = _loadingMore.asStateFlow()

    private var limit = 50
    private var offset = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokemonsWithImages()
        }
    }

    private suspend fun loadPokemonsWithImages() {
        _loading.value = true

        val pkResponse = pokemonRepository.getPokemons(limit, offset).body()
        val pokemons = pkResponse?.results ?: emptyList()

        val pokemonsList = pokemons.map { pk ->
            pokemonRepository.getOnePokemon(pokemons.indexOf(pk) + 1).body()
        }

        _pokemonsList.value = pokemonsList

        _loading.value = false

        offset += limit
    }

    fun filterPokemons(filter: String){
        if (filter.isEmpty()){
            viewModelScope.launch(Dispatchers.IO) {
                loadPokemonsWithImages()
            }
        } else {
            _pokemonsList.value = _pokemonsList.value.filter { it?.name?.contains(filter, ignoreCase = true) == true }
        }

    }
}