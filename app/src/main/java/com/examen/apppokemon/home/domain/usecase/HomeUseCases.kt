package com.examen.apppokemon.home.domain.usecase

import com.examen.apppokemon.home.domain.usecase.pokemonDetail.GetDetailPokemonUseCases

data class HomeUseCases(
 val getPokemonsUseCases: GetPokemonsUseCases,
 val getDetailPokemonUseCases: GetDetailPokemonUseCases
)
