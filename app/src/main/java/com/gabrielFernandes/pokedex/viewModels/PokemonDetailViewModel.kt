package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.networkRepositories.PokemonRepository
import com.gabrielFernandes.pokedex.networkRepositories.TypeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class PokemonDetailViewModel(
    private val pokemonRepository: PokemonRepository,
    private val typeRepository: TypeRepository
) : ViewModel() {

    private val _pokemon = MutableStateFlow<Pokemon?>(null)
    val pokemon = _pokemon.asStateFlow()

    private val _stats = MutableStateFlow<Map<String, Int>>(emptyMap())
    val stats = _stats.asStateFlow()

    private val _types = MutableStateFlow<List<String>>(emptyList())
    val types = _types.asStateFlow()

    private val _next = MutableStateFlow<Pokemon?>(null)
    val next = _next.asStateFlow()

    private val _before = MutableStateFlow<Pokemon?>(null)
    val before = _before.asStateFlow()

    suspend fun loadPokemon(id: Int) {
        val pk = pokemonRepository.getOnePokemon(id).body()

        pk?.let {
            _types.value = loadTypes(it)
            _stats.value = loadStats(it)
        }

        _pokemon.value = pk
    }

    private suspend fun loadTypes(pokemon: Pokemon) : List<String>{
        val types = mutableListOf<String>()

        pokemon.types.forEach { type ->
            typeRepository.getOneType(type.type.name).body()?.sprites?.generationVIII?.swordShiel?.nameIcon?.let {
                types.add(
                    it
                )
            }
        }

        return types
    }

    private fun loadStats(pokemon: Pokemon): Map<String, Int>{
        val stats: MutableMap<String, Int> = mutableMapOf()

        pokemon.stats.forEach { stat ->
            stats[stat.stat.name] = stat.baseStat
        }

        return stats
    }

    fun nextOrBeforePokemon(id: Int){
        viewModelScope.launch(Dispatchers.IO) {
            _pokemon.value = pokemonRepository.getOnePokemon(id).body()
            _next.value = pokemonRepository.getOnePokemon(id + 1).body()
            _before.value = pokemonRepository.getOnePokemon(id - 1).body()
        }
    }
}