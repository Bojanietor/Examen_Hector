package com.examen.apppokemon.home.data.repository

import com.examen.apppokemon.core.utils.NetworkHelper
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

class HomeRepositoryImpl(
    private val dao: PokemonDao,
    private val api: PokemonApi,
    private val networkHelper: NetworkHelper
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

    override suspend fun setPokemonByName(pokemon: Pokemon): Flow<List<Pokemon>> {
        dao.insertPokemon(pokemon.toEntity())
        return dao.getAllPokemonList().map {
            it.map { it.toDomain() } }
    }

    override fun hasInternet(): Boolean {
        return networkHelper.isInternetAvailable()
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