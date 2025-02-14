package com.examen.apppokemon.home.presentation

import androidx.lifecycle.DefaultLifecycleObserver
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ProcessLifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.apppokemon.detail_pokemon.presentation.DetailState
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.usecase.HomeUseCases
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeUseCases: HomeUseCases
) : ViewModel() {
   private val _state = MutableLiveData<HomeState>()
    val state : LiveData<HomeState> = _state

    init {
        observerPokemon()
        getPokemons()

    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowDetailPokemon -> {
                _state.value = HomeState( showDetail = true, selectedPokemonImage = event.urlImage, pokemons = state.value!!.pokemons)
            }
            is HomeEvent.HiddenDetailPokemon -> {
                _state.value = HomeState( showDetail = false, selectedPokemonImage = "", pokemons = state.value!!.pokemons)
            }
            is HomeEvent.onlikeOrDisLikePokemon->{
                _state.value = HomeState( pokemons = state.value!!.pokemons, isLoading = true )
                viewModelScope.launch {
                    homeUseCases.setLikeOrDislikePokemonUseCases(event.pokemon).collect {
                        _state.value = HomeState( pokemons = it, isLoading = false )
                    }
                }
            }
        }
    }

    private fun observerPokemon() {
        viewModelScope.launch {
            //Aqui tu decide pero creo tu tienes un caso de uso para esta parte, nomas retorna directo la info de el repository para no tanto rollo
            homeUseCases.observerPokemonUseCases().collect { pokemonList: List<Pokemon> ->
                _state.value = HomeState( pokemons = pokemonList, isLoading = false )
            }
        }
    }


    private fun getPokemons() {
        viewModelScope.launch {
            homeUseCases.getPokemonsUseCases(0, 25).collectLatest {
                _state.value = HomeState( pokemons = it, isLoading = false )
            }
        }
    }
}