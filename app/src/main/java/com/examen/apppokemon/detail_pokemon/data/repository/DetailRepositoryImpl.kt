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

    override suspend fun getPokemonByName(id: Long): Flow<Pokemon> {
        val localFlow = dao.getPokemon(id).map {
            it.toDomain() }

        val apiFlow = getPokemonFromApi(id)

        return  localFlow.combine(apiFlow){db,api ->
            db
        }
    }

    override suspend fun setPokemonByName(pokemon: Pokemon): Flow<Pokemon> {
        dao.insertPokemon(pokemon.toEntity())
        val localFlow = dao.getPokemon(pokemon.id!!).map {
            it.toDomain() }
        return localFlow.combine(localFlow){db,api ->
            db
        }
    }

    override suspend fun observerPokemon(id: Long): Flow<Pokemon> {
      return  dao.getPokemon(id).map { it.toDomain() }
    }

    private fun getPokemonFromApi(id: Long): Flow<Pokemon> {
        return flow {
            emit(api.getDetailPokemon(id = id))
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