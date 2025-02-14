package com.examen.apppokemon.home.domain.usecase

import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.repository.HomeRepository
import kotlinx.coroutines.flow.Flow

class OberverEthernetUseCase(
    private val repository: HomeRepository
) {

    operator fun invoke(): Boolean {
        return repository.hasInternet()
    }
}