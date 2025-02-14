package com.examen.apppokemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.examen.apppokemon.detail_pokemon.presentation.DetailPokemonDialog
import com.examen.apppokemon.home.presentation.HomeScreen


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
){
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Home.route) {
            HomeScreen(onDetailPokemon = {pokemonid, isFavorite ->
                navHostController.navigate(NavigationRoute.Detail.route + "?pokemonId=$pokemonid&isFavorite=$isFavorite")
            })
        }

        composable(
            NavigationRoute.Detail.route + "?pokemonId={pokemonId}&isFavorite={isFavorite}",
            arguments = listOf(
                navArgument("pokemonId") {
                    type = NavType.LongType
                    nullable = false
                    defaultValue = 1
                },
                navArgument("isFavorite") {
                    type = NavType.BoolType
                    nullable = false
                    defaultValue = false
                }
            )
        ) {
            DetailPokemonDialog(onBack = {
                navHostController.popBackStack()
            }, )
        }
    }


}