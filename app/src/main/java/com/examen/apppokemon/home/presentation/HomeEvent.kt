package com.examen.apppokemon.home.presentation


interface HomeEvent {
    data class ShowDetailPokemon(val name: String) : HomeEvent
    data class HiddenDetailPokemon(val showDetail: Boolean) : HomeEvent
}