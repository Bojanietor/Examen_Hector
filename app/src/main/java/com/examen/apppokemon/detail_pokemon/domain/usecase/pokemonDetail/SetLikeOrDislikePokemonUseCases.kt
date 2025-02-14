package com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail

import com.examen.apppokemon.detail_pokemon.domain.repository.DetailRepository
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

class SetLikeOrDislikePokemonUseCases(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(pokemon: Pokemon): Flow<Pokemon> {
        return repository.setPokemonByName(pokemon)
    }
}