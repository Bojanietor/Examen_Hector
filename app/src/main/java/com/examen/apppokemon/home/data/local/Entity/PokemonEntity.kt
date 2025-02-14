package com.examen.apppokemon.home.data.local.Entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity
data class PokemonEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long?,
    val name: String?,
    val sprites: String? = null,
    val height: Long? = null,
    val weight: Long? = null,
    val types: String? = null,
    var isFavorite: Boolean = false
)
