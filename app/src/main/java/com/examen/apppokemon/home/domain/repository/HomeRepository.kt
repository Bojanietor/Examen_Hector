package com.examen.apppokemon.home.domain.repository

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow

interface HomeRepository {
    fun getPokemons(offset: Int, limit: Int): Flow<List<Pokemon>>
    fun observerPokemon():  Flow<List<Pokemon>>
    suspend fun setPokemonByName(pokemon: Pokemon): Flow<List<Pokemon>>
    fun hasInternet() : Boolean
}