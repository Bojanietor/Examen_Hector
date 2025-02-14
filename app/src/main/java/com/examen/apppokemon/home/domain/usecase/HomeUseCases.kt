package com.examen.apppokemon.home.domain.usecase

data class HomeUseCases(
 val getPokemonsUseCases: GetPokemonsUseCases,
 val observerPokemonUseCases: ObserverPokemonUseCases,
 val setLikeOrDislikePokemonUseCases: SetLikeOrDislikePokemonHomeUseCases,
 val observerEthernetCases: OberverEthernetUseCase
)
