package com.examen.apppokemon.detail_pokemon.presentation

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.presentation.HomeEvent

interface DetailEvent {
    data class onlikeOrDisLikePokemon(val pokemon: Pokemon) : DetailEvent
}