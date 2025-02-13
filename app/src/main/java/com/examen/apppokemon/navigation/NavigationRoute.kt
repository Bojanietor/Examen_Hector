package com.examen.apppokemon.navigation

sealed class NavigationRoute(val route: String) {
    object Onboarding : NavigationRoute("onboarding")
    object Home : NavigationRoute("home")
}