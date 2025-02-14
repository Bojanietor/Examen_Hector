package com.examen.apppokemon.home.di

import android.content.Context
import androidx.room.Room
import com.examen.apppokemon.core.utils.AuthInterceptor
import com.examen.apppokemon.detail_pokemon.data.repository.DetailRepositoryImpl
import com.examen.apppokemon.detail_pokemon.domain.repository.DetailRepository
import com.examen.apppokemon.home.data.local.PokemonDao
import com.examen.apppokemon.home.data.local.PokemonDatabase
import com.examen.apppokemon.home.data.remote.PokemonApi
import com.examen.apppokemon.home.data.repository.HomeRepositoryImpl
import com.examen.apppokemon.home.domain.repository.HomeRepository
import com.examen.apppokemon.home.domain.usecase.GetPokemonsUseCases
import com.examen.apppokemon.home.domain.usecase.HomeUseCases
import com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail.DetailPokemonUseCases
import com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail.GetDetailPokemonUseCases
import com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail.ObserverPokemonDetailUseCases
import com.examen.apppokemon.detail_pokemon.domain.usecase.pokemonDetail.SetLikeOrDislikePokemonUseCases
import com.examen.apppokemon.home.domain.usecase.ObserverPokemonUseCases
import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object HomeScreenModule {

    @Singleton
    @Provides
    fun provideHomeUseCases(repository: HomeRepository): HomeUseCases {
        return HomeUseCases(
            getPokemonsUseCases = GetPokemonsUseCases(repository),
            observerPokemonUseCases = ObserverPokemonUseCases(repository)
        )
    }

    @Singleton
    @Provides
    fun provideDetailUseCases(repository: DetailRepository): DetailPokemonUseCases {
        return DetailPokemonUseCases(
            getDetailPokemonUseCases = GetDetailPokemonUseCases(repository),
            setLikeOrDislikePokemonUseCases = SetLikeOrDislikePokemonUseCases(repository),
            observerPokemonUseCases = ObserverPokemonDetailUseCases(repository)

        )
    }

    @Singleton
    @Provides
    fun providePokemonDao(@ApplicationContext context: Context): PokemonDao {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            "pokemons_db"
        ).build().dao
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(
            HttpLoggingInterceptor().apply {
                level = HttpLoggingInterceptor.Level.BODY
            }
        ).addInterceptor(AuthInterceptor()).build()
    }

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi.Builder().build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient, moshi: Moshi): Retrofit
    {    return Retrofit.Builder().baseUrl(PokemonApi.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(MoshiConverterFactory.create(moshi)).build()
    }

    @Singleton
    @Provides
    fun providePokemonApi(retrofit: Retrofit): PokemonApi {
        return retrofit.create(PokemonApi::class.java)
    }

    @Singleton
    @Provides
    fun provideHomeRepository(dao: PokemonDao, api: PokemonApi): HomeRepository {
        return HomeRepositoryImpl(dao, api)
    }

    @Singleton
    @Provides
    fun provideDetailRepository(dao: PokemonDao, api: PokemonApi): DetailRepository {
        return DetailRepositoryImpl(dao, api)
    }
}