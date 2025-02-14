package com.examen.apppokemon.detail_pokemon.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.examen.apppokemon.R
import com.examen.apppokemon.core.presentation.CircularAvatar
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon
import com.examen.apppokemon.home.presentation.HomeEvent
import com.examen.apppokemon.home.presentation.components.ExpandedImageDialog

@Composable
fun DetailPokemonDialog(
    viewModel : PokemonDetailViewModel = hiltViewModel(),
    onBack: () -> Unit,) {
    val state : DetailState? by viewModel.state.observeAsState()
    var backgroundColor = Color.Yellow
    var textColor = Color.Green
    if (state?.pokemon?.sprites?.frontDefault != null && state?.pokemon!!.sprites?.frontDefault?.isNotEmpty() == true){
        backgroundColor = Color.Transparent
        textColor = Color.Transparent
    }

        Box(
            modifier = Modifier
                .background(Color.White).fillMaxSize().padding(top = 30.dp)
        ) {
            Image(
                painter = painterResource(id = R.drawable.pokemon_background), // Reemplaza con tu imagen
                contentDescription = "Fondo",
                contentScale = ContentScale.Crop,
                modifier = Modifier.matchParentSize()
            )
            IconButton({onBack()}) {
                Icon(
                    modifier = Modifier.size(30.dp),
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = ""
                )
            }
            if(state == null || state!!.isLoading){
                CircularProgressIndicator(color = Color.Black, modifier = Modifier.align(Alignment.Center))
            }else {
                Column(
                    modifier = Modifier
                        .padding(20.dp).align(Alignment.Center),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                   if(state?.pokemon?.isFavorite == true){
                       Image(modifier = Modifier.size(17.dp).clickable {
                           val pokemonEdit : Pokemon = state!!.pokemon!!
                           pokemonEdit.isFavorite = !pokemonEdit.isFavorite
                           viewModel.onEvent(DetailEvent.onlikeOrDisLikePokemon(pokemonEdit))
                       }, painter = painterResource(id = R.drawable.corazon_like), contentDescription = "like")
                   }else{
                       Image( painter = painterResource(id = R.drawable.corazon_unlike), contentDescription = "unlike", modifier = Modifier.size(17.dp).clickable {
                           val pokemonEdit : Pokemon = state!!.pokemon!!
                           pokemonEdit.isFavorite = !pokemonEdit.isFavorite
                           viewModel.onEvent(DetailEvent.onlikeOrDisLikePokemon(pokemonEdit))
                       })
                   }

                    CircularAvatar(
                        imageUrl = state?.pokemon?.sprites?.frontDefault,
                        name = state?.pokemon?.name,
                        textColor = textColor,
                        fontSize = 30.sp,
                        backgroundColor = backgroundColor,
                        size = 100.dp,
                        onPokemonClickImage = {

                        }
                    )
                    Text("name: " + state?.pokemon?.name.toString())
                    Text("height: " + state?.pokemon?.height)
                    Text("weight: " + state?.pokemon?.weight)
                    Text("types: " + state?.pokemon?.types?.map { it.type?.name })
                }

            }
        }

}