package com.examen.apppokemon.detail_pokemon.domain.models.pokemonDetail

import android.os.Parcelable
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

@JsonClass(generateAdapter = true)
data class PokemonDetailV2Response (
    @Json(name = "base_experience") val baseExperience: Long? = null,
    val height: Long? = null,
    val id: Long? = null,
    val name: String? = null,
    val sprites: Sprites? = null,
    val types: List<Type>? = null,
    val weight: Long? = null
)
@JsonClass(generateAdapter = true)
@Parcelize
data class Species (
    val name: String? = null,
    val url: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationV (
    @Json(name = "black-white")   val blackWhite: Sprites? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationIv (
    @Json(name = "diamond-pearl") val diamondPearl: Sprites? = null,
    @Json(name = "heartgold-soulsilver")  val heartgoldSoulsilver: Sprites? = null,
    val platinum: Sprites? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Versions (
    @Json(name = "generation-i")  val generationI: GenerationI? = null,
    @Json(name = "generation-ii")  val generationIi: GenerationIi? = null,
    @Json(name = "generation-iii")  val generationIii: GenerationIii? = null,
    @Json(name = "generation-iv") val generationIv: GenerationIv? = null,
    @Json(name = "generation-v") val generationV: GenerationV? = null,
    @Json(name = "generation-vi") val generationVi: Map<String, Home>? = null,
    @Json(name = "generation-vii") val generationVii: GenerationVii? = null,
    @Json(name = "generation-viii") val generationViii: GenerationViii? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Other (
    @Json(name = "dream_world") val dreamWorld: DreamWorld? = null,
    val home: Home? = null,
    @Json(name = "official-artwork") val officialArtwork: OfficialArtwork? = null,
    val showdown: Sprites? = null
)  : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Sprites (
    @Json(name = "back_default") val backDefault: String? = null,
    @Json(name = "back_female") val backFemale: String? = null,
    @Json(name = "back_shiny") val backShiny: String? = null,
    @Json(name = "back_shiny_female") val backShinyFemale: String? = null,
    @Json(name = "front_default") val frontDefault: String? = null,
    @Json(name = "front_female")  val frontFemale: String? = null,
    @Json(name = "front_shiny") val frontShiny: String? = null,
    @Json(name = "front_shiny_female") val frontShinyFemale: String? = null,
    val other: Other? = null,
    val versions: Versions? = null,
    val animated: Sprites? = null
): Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationI (
    @Json(name = "red-blue") val redBlue: RedBlue? = null,
    val yellow: RedBlue? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class RedBlue (
    @Json(name = "back_default") val backDefault: String? = null,
    @Json(name = "back_gray") val backGray: String? = null,
    @Json(name = "back_transparent") val backTransparent: String? = null,
    @Json(name = "front_default") val frontDefault: String? = null,
    @Json(name = "front_gray") val frontGray: String? = null,
    @Json(name = "front_transparent") val frontTransparent: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationIi (
    val crystal: Crystal? = null,
    val gold: Gold? = null,
    val silver: Gold? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Crystal (
    @Json(name = "back_default") val backDefault: String? = null,
    @Json(name = "back_shiny") val backShiny: String? = null,
    @Json(name = "back_shiny_transparent") val backShinyTransparent: String? = null,
    @Json(name = "back_transparent") val backTransparent: String? = null,
    @Json(name = "front_default") val frontDefault: String? = null,
    @Json(name = "front_shiny") val frontShiny: String? = null,
    @Json(name = "front_shiny_transparent") val frontShinyTransparent: String? = null,
    @Json(name = "front_transparent")  val frontTransparent: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Gold (
    @Json(name = "back_default")  val backDefault: String? = null,
    @Json(name = "back_shiny")  val backShiny: String? = null,
    @Json(name = "front_default") val frontDefault: String? = null,
    @Json(name = "front_shiny") val frontShiny: String? = null,
    @Json(name = "front_transparent")  val frontTransparent: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationIii (
    val emerald: OfficialArtwork? = null,
    @Json(name = "firered-leafgreen") val fireredLeafgreen: Gold? = null,
    @Json(name = "ruby-sapphire")  val rubySapphire: Gold? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class OfficialArtwork (
    @Json(name = "front_default")    val frontDefault: String? = null,
    @Json(name = "front_shiny")  val frontShiny: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Home (
    @Json(name = "front_default") val frontDefault: String? = null,
    @Json(name = "front_female") val frontFemale: String? = null,
    @Json(name = "front_shiny") val frontShiny: String? = null,
    @Json(name = "front_shiny_female") val frontShinyFemale: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationVii (
    val icons: DreamWorld? = null,
    @Json(name = "ultra-sun-ultra-moon")  val ultraSunUltraMoon: Home? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class DreamWorld (
    @Json(name = "front_default")  val frontDefault: String? = null,
    @Json(name = "front_female") val frontFemale: String? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class GenerationViii (
    val icons: DreamWorld? = null
) : Parcelable
@JsonClass(generateAdapter = true)
@Parcelize
data class Type (
    val slot: Long? = null,
    val type: Species? = null
) : Parcelable

















