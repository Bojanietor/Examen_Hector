package com.examen.apppokemon.home.presentation.detail

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.apppokemon.home.domain.usecase.pokemonDetail.DetailPokemonUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val  detailPokemonUseCases : DetailPokemonUseCases
)  : ViewModel() {

    var state by mutableStateOf(DetailState())
        private set


    fun getPokemonDetail(name: String ) {
        viewModelScope.launch {
            detailPokemonUseCases.getDetailPokemonUseCases(name).collectLatest {
                state.pokemon = it
            }
        }
    }

}