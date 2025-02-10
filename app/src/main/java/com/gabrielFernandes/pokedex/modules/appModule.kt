package com.gabrielFernandes.pokedex.modules

import com.gabrielFernandes.pokedex.viewModels.MainViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    viewModel {
        MainViewModel(
            pokemonRepository = get()
        )
    }
}