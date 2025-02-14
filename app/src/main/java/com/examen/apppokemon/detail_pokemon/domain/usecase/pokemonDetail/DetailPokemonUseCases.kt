package com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail

data class DetailPokemonUseCases(
    val getDetailPokemonUseCases: GetDetailPokemonUseCases,
    val setLikeOrDislikePokemonUseCases: SetLikeOrDislikePokemonUseCases,
    val observerPokemonUseCases: ObserverPokemonDetailUseCases
)