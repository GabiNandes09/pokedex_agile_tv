package com.gabrielFernandes.pokedex.modules

import com.gabrielFernandes.pokedex.viewModels.MainViewModel
import com.gabrielFernandes.pokedex.viewModels.PokemonDetailViewModel
import com.gabrielFernandes.pokedex.viewModels.PokemonPreviewViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            pokemonRepository = get(),
            network = get()
        )
    }
    factory {
        PokemonPreviewViewModel(
            typeRepository = get()
        )
    }
    viewModel {
        PokemonDetailViewModel(
            pokemonRepository = get(),
            typeRepository = get()
        )
    }
}