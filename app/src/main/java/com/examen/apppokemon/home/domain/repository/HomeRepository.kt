package com.examen.apppokemon.home.domain.repository

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemons(offset: Int, limit: Int): Flow<List<Pokemon>>
    suspend fun getPokemonByName(name: String): Flow<Pokemon>
}