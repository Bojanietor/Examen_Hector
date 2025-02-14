package com.examen.apppokemon.home.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.examen.apppokemon.home.data.local.Entity.PokemonEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity")
    fun getAllPokemonList(): Flow<List<PokemonEntity>>

    @Query("SELECT * FROM PokemonEntity WHERE PokemonEntity.id = :id LIMIT 1")
    fun getPokemon(id: Long): Flow<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertHabits(pokemonEntitys: List<PokemonEntity>)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPokemon(pokemonEntity: PokemonEntity)
}