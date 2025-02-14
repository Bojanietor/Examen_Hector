package com.examen.apppokemon.detail_pokemon.presentation

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon

data class DetailState(
    var pokemon: Pokemon? = null,
    var isLoading: Boolean = true,

)
