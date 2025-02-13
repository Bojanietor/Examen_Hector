package com.examen.apppokemon.home.domain.models.Pokemon

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class PokemonListResponse (
          val count: Long? = null,
          val next: String? = null,
          val previous: String? = null,
          val results: List<Pokemon>? = null
)


