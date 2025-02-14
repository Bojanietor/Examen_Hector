package com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail

import com.examen.apppokemon.detail_pokemon.domain.repository.DetailRepository
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class ObserverPokemonDetailUseCases(
    private val repository: DetailRepository
) {

    suspend operator fun invoke(id: Long): Flow<Pokemon> {
        return repository.observerPokemon(id)
    }
}