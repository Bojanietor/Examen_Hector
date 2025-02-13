package com.examen.apppokemon.home.data.repository

import com.examen.apppokemon.home.data.local.PokemonDao
import com.examen.apppokemon.home.data.local.Entity.PokemonEntity
import com.examen.apppokemon.home.data.mapper.toDomain
import com.examen.apppokemon.home.data.mapper.toEntity
import com.examen.apppokemon.home.data.remote.PokemonApi
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class HomeRepositoryImpl(
    private val dao: PokemonDao,
    private val api: PokemonApi
) : HomeRepository {
    override fun getPokemons(offset: Int, limit: Int): Flow<List<Pokemon>> {
        val localFlow = dao.getAllPokemonList().map {
            it.map { it.toDomain() } }

        val apiFlow = getAllPokemonsFromApi(offset = offset, limit = limit)

        return localFlow.combine(apiFlow){db, _ ->
            db
        }
    }

    override suspend fun getPokemonByName(name: String): Flow<Pokemon> {
        return getPokemonFromApi(name)
    }

    private fun getAllPokemonsFromApi(offset: Int, limit: Int): Flow<List<Pokemon>> {
        return flow {
            emit(api.getAllPokemons(limit = limit, offset = offset))
        }.map {
            val pokemons = it.toDomain()
            insertHabits(pokemons)
            pokemons
        }
    }

    private fun getPokemonFromApi(name: String): Flow<Pokemon> {
        return flow {
            emit(api.getDetailPokemon(name = name))
        }.map {
            val pokemon = it.toEntity()
            insertHabit(pokemon)
            pokemon.toDomain()
        }
    }

    private suspend fun insertHabit(pokemon: PokemonEntity) {
        dao.insertPokemon(pokemon)
    }

    private suspend fun insertHabits(pokemons: List<Pokemon>) {
        dao.insertHabits(pokemons.map { it.toEntity() })
    }
}