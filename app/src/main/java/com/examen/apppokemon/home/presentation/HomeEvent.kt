package com.examen.apppokemon.home.presentation

import com.examen.apppokemon.detail_pokemon.presentation.DetailEvent
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon


interface HomeEvent {
    data class ShowDetailPokemon(val urlImage: String) : HomeEvent
    data class HiddenDetailPokemon(val showDetail: Boolean) : HomeEvent
    data class onlikeOrDisLikePokemon(val pokemon: Pokemon) : HomeEvent
}