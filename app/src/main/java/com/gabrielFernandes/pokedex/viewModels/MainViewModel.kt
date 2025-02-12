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

    private val _names = MutableStateFlow<List<String>>(emptyList())
    val names = _names.asStateFlow()

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loadingMore = MutableStateFlow(false)
    val loadingMore = _loadingMore.asStateFlow()

    private var limit = 50
    private var offset = 0

    init {
        viewModelScope.launch(Dispatchers.IO) {
            loadPokemonsWithImages(
                limit = limit,
                offset = offset
            )
            loadNames()
        }
    }

    /**
     * Usado apenas no Init ou quando a lista fica vazia para carregar os primeiros
     */
    private suspend fun loadPokemonsWithImages(limit: Int, offset: Int) {
        _loading.value = true

        val pkResponse = pokemonRepository.getPokemons(limit, offset).body()
        val pokemons = pkResponse?.results ?: emptyList()

        val pokemonsList = pokemons.map { pk ->
            pokemonRepository.getOnePokemon(pokemons.indexOf(pk) + 1).body()
        }

        _pokemonsList.value = pokemonsList

        _loading.value = false

        this.offset += limit
    }

    fun filterPokemons(filter: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (filter.isEmpty()) {
                offset = 0
                loadPokemonsWithImages(limit, offset)
            } else {
                _pokemonsList.value =
                    _pokemonsList.value.filter {
                        it?.name?.contains(
                            filter,
                            ignoreCase = true
                        ) == true
                    }

                _loadingMore.value = true

                val list = _names.value.filter { it.contains(filter, ignoreCase = true) }

                if (list.isNotEmpty()) {
                    _pokemonsList.value = list.map {
                        pokemonRepository.getOnePokemon(it).body()
                    }
                }

                _loadingMore.value = false
            }
        }
    }


    fun loadMorePokemons() {
        if (!_loadingMore.value && !_loading.value) {
            _loadingMore.value = true

            viewModelScope.launch(Dispatchers.IO) {
                val newPk =
                    pokemonRepository.getPokemons(limit, offset).body()?.results ?: emptyList()

                val newList = newPk.mapIndexed { index, _ ->
                    pokemonRepository.getOnePokemon(offset + index + 1).body()
                }

                _pokemonsList.value += newList

                _loadingMore.value = false

                offset += limit
            }
        }
    }

    private suspend fun loadNames() {
        _names.value = pokemonRepository.getPokemons(
            limit = 1304,
            offset = 0
        ).body()?.results?.map { it.name } ?: emptyList()
    }
}