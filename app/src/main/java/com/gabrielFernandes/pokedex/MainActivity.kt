package com.gabrielFernandes.pokedex

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.gabrielFernandes.pokedex.ui.screen.mainUI.MainUI
import com.gabrielFernandes.pokedex.ui.screen.pokemonDetails.PokemonDetailUI
import com.gabrielFernandes.pokedex.ui.theme.PokedexTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            PokedexTheme {
                val navController = rememberNavController()

                NavHost(navController = navController, startDestination = "main" ){
                    composable("main"){
                        MainUI(navController)
                    }
                    composable("pokemon/{id}"){ entry ->
                        entry.arguments?.getString("id")?.let { id ->
                            PokemonDetailUI(id = id.toInt())
                        }
                    }
                }
            }
        }
    }
}