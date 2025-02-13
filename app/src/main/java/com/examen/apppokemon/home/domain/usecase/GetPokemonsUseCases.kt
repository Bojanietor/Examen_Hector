package com.examen.apppokemon.home.domain.usecase

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class GetPokemonsUseCases(
    private val repository: HomeRepository
) {
    operator fun invoke(offset: Int, limit: Int): Flow<List<Pokemon>> {
        return repository.getPokemons(offset,limit).distinctUntilChanged().map {
            it
        }
    }
}