package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import com.gabrielFernandes.pokedex.models.Pokemon
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class MainViewModel(

) : ViewModel() {
    private val _pokemons = MutableStateFlow<List<Pokemon>>(emptyList())
    private val pokemons = _pokemons.asStateFlow()


}