package com.examen.apppokemon.navigation

sealed class NavigationRoute(val route: String) {
    object Detail : NavigationRoute("detail")
    object Home : NavigationRoute("home")
}