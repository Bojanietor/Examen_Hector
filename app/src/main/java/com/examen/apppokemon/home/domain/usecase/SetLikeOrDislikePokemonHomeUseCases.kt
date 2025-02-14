package com.examen.apppokemon.home.domain.usecase

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class SetLikeOrDislikePokemonHomeUseCases(
    private val repository: HomeRepository
) {
    suspend operator fun invoke(pokemon: Pokemon): Flow<List<Pokemon>> {
        return repository.setPokemonByName(pokemon)
    }
}