package com.examen.apppokemon.home.domain.usecase

import com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail.GetDetailPokemonUseCases

data class HomeUseCases(
 val getPokemonsUseCases: GetPokemonsUseCases,
 val observerPokemonUseCases: ObserverPokemonUseCases
)
