package com.examen.apppokemon.home.presentation

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.apppokemon.home.domain.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {
    var state by mutableStateOf(HomeState())
        private set

    init {
        getPokemons()
    }

    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowDetailPokemon -> {
                state = state.copy(
                    showDetail = true,
                    selectedPokemon = event.name
                )
            }
            is HomeEvent.HiddenDetailPokemon -> {
                state = state.copy(
                    showDetail = false,
                    selectedPokemon = ""
                )
            }
        }
    }


    private fun getPokemons() {
        viewModelScope.launch {
            homeUseCases.getPokemonsUseCases(0, 25).collectLatest {
                state = state.copy(
                    pokemons = it
                )
            }
        }
    }
}