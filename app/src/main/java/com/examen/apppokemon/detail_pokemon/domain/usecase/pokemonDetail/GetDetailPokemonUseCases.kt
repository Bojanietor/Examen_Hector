package com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail

import com.examen.apppokemon.detail_pokemon.domain.repository.DetailRepository
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class GetDetailPokemonUseCases(
    private val repository: DetailRepository
) {
    suspend operator fun invoke(name: String): Flow<Pokemon> {
        return repository.getPokemonByName(name)
    }
}