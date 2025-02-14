package com.examen.apppokemon.detail_pokemon.domain.repository

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface DetailRepository {
    suspend fun getPokemonByName(id: Long, isFavorite: Boolean): Flow<Pokemon>
    suspend fun setPokemonByName(pokemon: Pokemon): Flow<Pokemon>
    suspend fun observerPokemon(id: Long): Flow<Pokemon>
}