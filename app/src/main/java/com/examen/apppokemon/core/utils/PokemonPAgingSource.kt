package com.examen.apppokemon.core.utils

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.examen.apppokemon.home.data.remote.PokemonApi
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon

class PokemonPagingSource(
    private val apiService: PokemonApi
) : PagingSource<Int, Pokemon>() {

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, Pokemon> {
        return try {
            val currentPage = params.key ?: 0
            val response = apiService.getAllPokemons(limit = 25, offset = currentPage * 25)

            LoadResult.Page(
                data = response.results,
                prevKey = if (currentPage == 0) null else currentPage - 1,
                nextKey = if (response.results.isEmpty()) null else currentPage + 1
            )
        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

    override fun getRefreshKey(state: PagingState<Int, Pokemon>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }
}
