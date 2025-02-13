package com.examen.apppokemon.home.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.examen.apppokemon.home.data.local.Entity.PokemonEntity


@Database(entities = [PokemonEntity::class], version = 1)
abstract class PokemonDatabase : RoomDatabase() {
    abstract val dao: PokemonDao
}