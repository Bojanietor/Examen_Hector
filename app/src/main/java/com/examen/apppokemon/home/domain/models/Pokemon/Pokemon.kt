package com.examen.apppokemon.home.domain.models.Pokemon

import android.os.Parcelable
import com.examen.apppokemon.detail_pokemon.domain.models.pokemonDetail.Sprites
import com.examen.apppokemon.detail_pokemon.domain.models.pokemonDetail.Type
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
@Parcelize
data class Pokemon (
    val id: Long?= null,
    val name: String? = null,
    val url: String? = null,
    val sprites: Sprites? = null,
    val height: Long? = null,
    val weight: Long? = null,
    val types: List<Type>? = null,
    var isFavorite: Boolean = false
) : Parcelable
