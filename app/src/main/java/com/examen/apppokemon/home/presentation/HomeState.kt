package com.examen.apppokemon.home.presentation

import android.os.Parcelable
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import kotlinx.parcelize.Parcelize

@Parcelize
data class HomeState(
    val pokemons: List<Pokemon> = emptyList(),
    val showDetail: Boolean = false,
    val selectedPokemon: String = "",
    val selectedPokemonImage: String = "",
    val isLoading : Boolean = true,
    val isLoadingList : Boolean = false,
    val offset : Int = 0,
    val limit: Int = 25
): Parcelable
