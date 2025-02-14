package com.examen.apppokemon.home.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
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
        _state.value = HomeState()
        observerPokemon()
        getPokemons()

    }


    fun onEvent(event: HomeEvent) {
        when (event) {
            is HomeEvent.ShowDetailPokemon -> {
                _state.value = HomeState( showDetail = true, selectedPokemonImage = event.urlImage, pokemons = state.value!!.pokemons, isLoading = false)
            }
            is HomeEvent.HiddenDetailPokemon -> {
                _state.value = HomeState( showDetail = false, selectedPokemonImage = "", pokemons = state.value!!.pokemons, isLoading = false)
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
                if (pokemonList.isNotEmpty())  _state.value = HomeState( pokemons = pokemonList, isLoading = false, offset = pokemonList.size  , limit = pokemonList.size + 25)
            }
        }
    }


    private fun getPokemons() {
        if(state.value != null){
            viewModelScope.launch {
                homeUseCases.getPokemonsUseCases(offset = state.value!!.offset, limit = _state.value!!.limit).collectLatest {
                    _state.value = HomeState( pokemons = it, isLoading = false , offset = it.size  , limit = _state.value!!.limit + 25)
                }
            }
        }
    }

    fun loadMorePokemons() {
        if (!_state.value!!.isLoadingList && homeUseCases.observerEthernetCases()){
            viewModelScope.launch {
            _state.value = HomeState(
                pokemons = state.value!!.pokemons,
                isLoading = false,
                isLoadingList = true,
                offset = _state.value!!.offset,
                limit =  _state.value!!.limit
            )
            homeUseCases.getPokemonsUseCases( offset = state.value!!.offset, limit = _state.value!!.limit).collectLatest {
                if(_state.value!!.pokemons.size < it.size ){
                    _state.value = HomeState(pokemons = it, isLoading = false, isLoadingList = false , offset = it.size, limit = it.size + 25 )
                }else{
                    _state.value = HomeState(
                        pokemons = state.value!!.pokemons,
                        isLoading = false,
                        isLoadingList = false,
                        offset = _state.value!!.offset,
                        limit =  _state.value!!.limit
                    )
                }

            }
        }
        }

    }
}