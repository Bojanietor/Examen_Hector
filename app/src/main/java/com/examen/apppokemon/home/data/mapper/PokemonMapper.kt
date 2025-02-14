package com.examen.apppokemon.home.data.mapper

import com.examen.apppokemon.home.data.local.Entity.PokemonEntity
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.domain.models.Pokemon.PokemonListResponse
import com.examen.apppokemon.detail_pokemon.domain.models.pokemonDetail.PokemonDetailV2Response
import com.examen.apppokemon.detail_pokemon.domain.models.pokemonDetail.Sprites
import com.examen.apppokemon.detail_pokemon.domain.models.pokemonDetail.Type
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types

fun PokemonEntity.toDomain(): Pokemon {

    var sprites: Sprites? = null
    var tagList: List<Type>? = null
    val moshi = Moshi.Builder()
        .build()
    if (this.types != null){
        val listType = Types.newParameterizedType(List::class.java, Type::class.java)
        val typeAdapter = moshi.adapter<List<Type>>(listType)
        tagList = typeAdapter.fromJson(this.types)
    }

    if(this.sprites != null){
        val jsonAdapter = moshi.adapter(Sprites::class.java)
        sprites  = jsonAdapter.fromJson(this.sprites)
    }
    return Pokemon(
        id = this.id,
        name = this.name,
        sprites = sprites,
        height = this.height,
        weight = this.weight,
        types = tagList,
        isFavorite = this.isFavorite
    )
}

fun Pokemon.toEntity(): PokemonEntity {
    if (this.url != null){
        var id = "1"
        val url = this.url.toString()

        val regex = """/pokemon/(\d+)/""".toRegex()
        val match = regex.find(url)

        if (match != null) {
            id = match.groupValues[1]
        }
        return PokemonEntity(
            id = id.toLong(),
            name = this.name,
        )

    }else{
        val moshi: Moshi = Moshi.Builder().build()
        val jsonAdapter: JsonAdapter<List<Type>> = moshi.adapter<List<Type>>(List::class.java)
        val typesString: String = jsonAdapter.toJson(this.types)

        val jsonAdapterSprite = moshi.adapter(Sprites::class.java)

        val spriteString = jsonAdapterSprite.toJson(this.sprites)
        return PokemonEntity(
            id = this.id,
            name = this.name,
            sprites = spriteString,
            height = this.height,
            weight = this.weight,
            types = typesString,
            isFavorite = this.isFavorite
        )
    }

}

fun PokemonListResponse.toDomain(): List<Pokemon> {
    return this.results?.map {
        Pokemon(
            name = it.name,
            url = it.url
        )
    } ?: listOf()
}


fun PokemonDetailV2Response.toEntity(): PokemonEntity {
    val moshi: Moshi = Moshi.Builder().build()
    val jsonAdapter: JsonAdapter<List<Type>> = moshi.adapter<List<Type>>(List::class.java)
    val typesString: String = jsonAdapter.toJson(this.types)

    val jsonAdapterSprite = moshi.adapter(Sprites::class.java)

    val spriteString = jsonAdapterSprite.toJson(this.sprites)
    return PokemonEntity(
        id = this.id,
        name = this.name,
        sprites = spriteString,
        height = this.height,
        weight = this.weight,
        types =  typesString ,
    )
}

