package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.networkRepositories.PokemonRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _pokemonsList = MutableStateFlow<List<Pokemon?>>(emptyList())
    val pokemonsList = _pokemonsList.asStateFlow()

    private val _names = MutableStateFlow<List<String>>(emptyList())

    private val _loading = MutableStateFlow(false)
    val loading = _loading.asStateFlow()

    private val _loadingMore = MutableStateFlow(false)
    val loadingMore = _loadingMore.asStateFlow()

    private val _filtering = MutableStateFlow(false)
    val filtering = _filtering.asStateFlow()

    private var limit = 20
    private var offset = 0

    private var searchJob: Job? = null

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

        withContext(Dispatchers.Main){
            _pokemonsList.value = pokemonsList
        }

        _loading.value = false

        this.offset += limit
    }

    fun filterPokemons(filter: String) {
        searchJob?.cancel()

        _filtering.value = true

        searchJob = viewModelScope.launch(Dispatchers.IO) {
            if (filter.isEmpty()) {
                offset = 0
                _filtering.value = false
                _loadingMore.value = false
                loadPokemonsWithImages(limit, offset)
            } else {
                val filteredList =
                    _pokemonsList.value.filter {
                        it?.name?.contains(
                            filter,
                            ignoreCase = true
                        ) == true || it?.id.toString().contains(filter)
                    }

                withContext(Dispatchers.Main){
                    _pokemonsList.value = filteredList
                }

                _loadingMore.value = true

                val list = _names.value.filter {
                    it.contains(
                        filter,
                        ignoreCase = true
                    ) || _names.value.indexOf(it).plus(1).toString().contains(filter)
                }

                if (list.isNotEmpty()) {
                    val new = list.map {
                        pokemonRepository.getOnePokemon(it).body()
                    }
                    withContext(Dispatchers.Main){
                        _pokemonsList.value = new
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

                withContext(Dispatchers.Main){
                    _pokemonsList.value += newList
                }

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