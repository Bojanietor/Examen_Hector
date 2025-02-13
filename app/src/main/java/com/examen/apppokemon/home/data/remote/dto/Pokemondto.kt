package com.examen.apppokemon.home.data.remote.dto

data class Pokemondto (
    val id: Long?,
    val name: String?,
    val sprites: String?,
    val height: Long? = null,
    val weight: Long? = null,
    val types: String? = null,
    val isFavorite: Boolean = false
    )