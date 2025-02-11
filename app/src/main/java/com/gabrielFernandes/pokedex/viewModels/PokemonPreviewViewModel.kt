package com.gabrielFernandes.pokedex.viewModels

import androidx.lifecycle.ViewModel
import com.gabrielFernandes.pokedex.models.Pokemon
import com.gabrielFernandes.pokedex.networkRepositories.TypeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class PokemonPreviewViewModel(
    private val typeRepository: TypeRepository
) : ViewModel() {

    private val _typeList = MutableStateFlow<List<String>>(emptyList())
    val typeList = _typeList.asStateFlow()

    suspend fun loadTypes(pokemon: Pokemon) {
        val types = mutableListOf<String>()

        pokemon.types.forEach { type ->
            typeRepository.getOneType(type.type.name).body()?.sprites?.generationVIII?.swordShiel?.nameIcon?.let {
                types.add(
                    it
                )
            }
        }

        _typeList.value = types
    }
}