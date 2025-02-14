package com.examen.apppokemon.detail_pokemon.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail.DetailPokemonUseCases
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.presentation.HomeState
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import okhttp3.internal.notify
import okhttp3.internal.notifyAll
import javax.inject.Inject

@HiltViewModel
class PokemonDetailViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val  detailPokemonUseCases : DetailPokemonUseCases
)  : ViewModel() {

    val _state = MutableLiveData<DetailState>()
    val state : LiveData<DetailState> = _state
    init {
        val pokemonId = savedStateHandle.get<Long?>("pokemonId")
        if (pokemonId != null) {
            observerPokemon(pokemonId)
            getPokemonDetail(pokemonId)

        }
    }

    fun onEvent(event: DetailEvent) {
        when (event) {
            is DetailEvent.onlikeOrDisLikePokemon -> {
                _state.value = DetailState(isLoading = true)
                viewModelScope.launch {
                    detailPokemonUseCases.setLikeOrDislikePokemonUseCases(event.pokemon).collectLatest {
                        _state.value = DetailState(pokemon = it, isLoading = false)

                    }
                }
            }
        }


    }

    private fun observerPokemon(id: Long) {
        viewModelScope.launch {
            //Aqui tu decide pero creo tu tienes un caso de uso para esta parte, nomas retorna directo la info de el repository para no tanto rollo
            detailPokemonUseCases.observerPokemonUseCases(id).collect { pokemon: Pokemon ->
                _state.value = DetailState(pokemon = pokemon, isLoading = false)
            }
        }
    }

        fun getPokemonDetail(id: Long ) {
        viewModelScope.launch {
            detailPokemonUseCases.getDetailPokemonUseCases(id).collectLatest {
                _state.value = DetailState(pokemon = it, isLoading = false)
            }
        }
    }

}

