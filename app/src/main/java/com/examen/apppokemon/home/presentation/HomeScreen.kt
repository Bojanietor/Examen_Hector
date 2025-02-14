package com.examen.apppokemon.home.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.examen.apppokemon.R
import com.examen.apppokemon.home.presentation.components.ExpandedImageDialog
import com.examen.apppokemon.home.presentation.components.RowItemPokemon

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel = hiltViewModel(),
    onDetailPokemon: (Long, Boolean) -> Unit,
    modifier: Modifier = Modifier
) {
    val state : HomeState? by viewModel.state.observeAsState()
    val rvState = rememberLazyListState() // Estado del scroll
    LaunchedEffect(rvState) {
        snapshotFlow { rvState.layoutInfo.visibleItemsInfo.lastOrNull()?.index }
            .collect { lastVisibleIndex ->
                if (lastVisibleIndex != null &&  state != null  && state!!.pokemons.isNotEmpty()  && lastVisibleIndex >= state!!.pokemons.size - 1 ) {
                    viewModel.loadMorePokemons() // Llamar más datos
                }
            }
    }
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(title = {
                Text(text = "Home")
            }, navigationIcon = {
                IconButton(onClick = {}) {
                    Icon(imageVector = Icons.Default.Settings, contentDescription = "settings")
                }
            })
        }
    ) {

        Box(modifier = modifier.background(Color.White).fillMaxSize(), contentAlignment = Alignment.Center) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_background), // Reemplaza con tu imagen
                contentDescription = "Fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize())

            if(state == null || state!!.isLoading) {
                CircularProgressIndicator(color = Color.Black, modifier = Modifier.align(Alignment.Center))
            }else {

                Column(
                    modifier = Modifier

                        .padding(20.dp),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {


                    LazyColumn( state = rvState,
                        modifier = Modifier
                            .padding(it)
                            .padding(start = 20.dp),
                        verticalArrangement = Arrangement.spacedBy(10.dp),
                        contentPadding = PaddingValues(bottom = 20.dp)
                    ) {
                        items(state!!.pokemons) { pokemon ->
                            RowItemPokemon(
                                pokemon = pokemon,
                                onPokemonClick = {
                                    if(pokemon.id != null) onDetailPokemon(pokemon.id, pokemon.isFavorite)
                                                 },
                                onPokemonClickImage = {
                                    if (pokemon.sprites != null)  viewModel.onEvent(event = HomeEvent.ShowDetailPokemon(pokemon.sprites.frontDefault.toString()))
                                },
                                onSelectFavorite = {pokemon ->
                                    viewModel.onEvent(HomeEvent.onlikeOrDisLikePokemon(pokemon))
                                }
                            )
                        }
                        if (state!!.isLoadingList) {
                            item {
                                CircularProgressIndicator(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(16.dp)
                                        .wrapContentWidth(Alignment.CenterHorizontally)
                                )
                            }
                        }
                    }

                }
            }
            if (state != null &&  state!!.showDetail) {
                ExpandedImageDialog(imageUrl = state!!.selectedPokemonImage,onDismiss = { viewModel.onEvent(
                    HomeEvent.HiddenDetailPokemon(showDetail = false)) })
            }
        }

    }
}