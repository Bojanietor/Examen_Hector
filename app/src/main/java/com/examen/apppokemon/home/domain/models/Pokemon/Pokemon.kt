package com.examen.apppokemon.home.domain.models.Pokemon

import com.examen.apppokemon.home.domain.models.pokemonDetail.Sprites
import com.examen.apppokemon.home.domain.models.pokemonDetail.Type
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Pokemon (
    val id: Long?= null,
    val name: String? = null,
    val url: String? = null,
    val sprites: Sprites? = null,
    val height: Long? = null,
    val weight: Long? = null,
    val types: List<Type>? = null,
    val isFavorite: Boolean = false
)
