package com.examen.apppokemon.home.presentation

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon

data class HomeState(
    val pokemons: List<Pokemon> = emptyList(),
    val showDetail: Boolean = false,
    val selectedPokemon: String = ""
)
