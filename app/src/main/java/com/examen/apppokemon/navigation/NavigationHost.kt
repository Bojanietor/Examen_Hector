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
            HomeScreen(onDetailPokemon = {
                navHostController.navigate(NavigationRoute.Detail.route + "?pokemonName=$it")
            })
        }

        composable(
            NavigationRoute.Detail.route + "?pokemonName={pokemonName}",
            arguments = listOf(
                navArgument("pokemonName") {
                    type = NavType.StringType
                    nullable = true
                    defaultValue = null
                }
            )
        ) {
            DetailPokemonDialog(onBack = {
                navHostController.popBackStack()
            }, )
        }
    }


}