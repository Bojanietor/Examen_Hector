package com.examen.apppokemon.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.examen.apppokemon.home.presentation.HomeScreen


@Composable
fun NavigationHost(
    navHostController: NavHostController,
    startDestination: NavigationRoute
){
    NavHost(navController = navHostController, startDestination = startDestination.route) {
        composable(NavigationRoute.Home.route) {
            HomeScreen(onDetailPokemon = {})
        }
    }
}