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
    private val _pokemonsList = MutableStateFlow<List<PokemonResult>?>(emptyList())
    val pokemonsList = _pokemonsList.asStateFlow()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokemons(50, 0)
        }
    }

   suspend fun loadPokemons(limit: Int, offset: Int){
       val pkResponse = pokemonRepository.getPokemons(limit, offset).body()
        _pokemonsList.value = pkResponse?.results
    }
}