package com.examen.apppokemon.home.data.remote


import com.examen.apppokemon.home.domain.models.Pokemon.PokemonListResponse
import com.examen.apppokemon.home.domain.models.pokemonDetail.PokemonDetailV2Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface PokemonApi {
    companion object {
        const val BASE_URL = "https://pokeapi.co/api/v2/pokemon/"
    }

    @GET(" ")
    suspend  fun getAllPokemons(@Query("offset") offset: Int, @Query("limit") limit: Int): PokemonListResponse



    @GET("{name}")
    suspend fun  getDetailPokemon(@Path("name") name: String) : PokemonDetailV2Response

}