package com.examen.apppokemon.detail_pokemon.data.repository

import com.examen.apppokemon.detail_pokemon.domain.repository.DetailRepository
import com.examen.apppokemon.home.data.local.Entity.PokemonEntity
import com.examen.apppokemon.home.data.local.PokemonDao
import com.examen.apppokemon.home.data.mapper.toDomain
import com.examen.apppokemon.home.data.mapper.toEntity
import com.examen.apppokemon.home.data.remote.PokemonApi
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map

class DetailRepositoryImpl (
    private val dao: PokemonDao,
    private val api: PokemonApi
): DetailRepository {

    override suspend fun getPokemonByName(name: String): Flow<Pokemon> {
        val localFlow = dao.getPokemon(name).map {
            it.toDomain() }

        val apiFlow = getPokemonFromApi(name)

        return  localFlow.combine(apiFlow){db,api ->
            db
        }
    }

    override suspend fun setPokemonByName(pokemon: Pokemon): Flow<Pokemon> {
        dao.insertPokemon(pokemon.toEntity())
        val localFlow = dao.getPokemon(pokemon.name!!).map {
            it.toDomain() }
        return localFlow.combine(localFlow){db,api ->
            db
        }
    }

    override suspend fun observerPokemon(name: String): Flow<Pokemon> {
      return  dao.getPokemon(name).map { it.toDomain() }
    }

    private fun getPokemonFromApi(name: String): Flow<Pokemon> {
        return flow {
            emit(api.getDetailPokemon(name = name))
        }.map {
            val pokemon = it.toEntity()
            insertHabit(pokemon)
            pokemon.toDomain()
        }.catch {
            emptyList<Pokemon>()
        }
    }

    private suspend fun insertHabit(pokemon: PokemonEntity) {
        dao.insertPokemon(pokemon)
    }
}