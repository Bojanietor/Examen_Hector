package com.examen.apppokemon.home.data.repository

import com.examen.apppokemon.home.data.local.Entity.PokemonEntity
import com.examen.apppokemon.home.data.local.PokemonDao
import com.examen.apppokemon.home.data.mapper.toDomain
import com.examen.apppokemon.home.data.mapper.toEntity
import com.examen.apppokemon.home.data.remote.PokemonApi
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.zip

class HomeRepositoryImpl(
    private val dao: PokemonDao,
    private val api: PokemonApi
) : HomeRepository {
    override fun getPokemons(offset: Int, limit: Int): Flow<List<Pokemon>> {
        val localFlow = dao.getAllPokemonList().map {
            it.map { it.toDomain() } }

        val apiFlow = getAllPokemonsFromApi(offset = offset, limit = limit)


        return localFlow.combine(apiFlow){db,api->
            db
        }
    }

    override fun observerPokemon(): Flow<List<Pokemon>> {
      return   dao.getAllPokemonList().map{ it.map { it.toDomain() }}

    }


    private fun getAllPokemonsFromApi(offset: Int, limit: Int): Flow<List<Pokemon>> {
        return flow {
            emit(api.getAllPokemons(limit = limit, offset = offset))
        }.onStart {
            emptyList<Pokemon>()
        }.catch {e ->
            println("Error al obtener pokemons de la API: ${e.message}")
           emptyList<Pokemon>()
        }.map {
            val pokemons = it.toDomain()
            insertHabits(pokemons)
            pokemons
        }
    }


    private suspend fun insertHabits(pokemons: List<Pokemon>) {
        dao.insertHabits(pokemons.map { it.toEntity() })
    }
}