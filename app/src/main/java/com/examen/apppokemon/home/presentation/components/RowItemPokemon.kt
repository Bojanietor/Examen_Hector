package com.examen.apppokemon.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.examen.apppokemon.R
import com.examen.apppokemon.core.presentation.CircularAvatar
import com.examen.apppokemon.detail_pokemon.presentation.DetailEvent
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon

@Composable
fun RowItemPokemon(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onPokemonClick: () -> Unit,
    onPokemonClickImage: (String) -> Unit
){

    Surface(
        shadowElevation = 8.dp, // Define la elevación
        shape = MaterialTheme.shapes.medium, // Forma opcional
        modifier = Modifier.padding(16.dp)
    ) {
        var backgroundColor = Color.Yellow
        var textColor = Color.Green
        if (pokemon.sprites?.frontDefault != null && pokemon.sprites.frontDefault.isNotEmpty()){
            backgroundColor = Color.White
            textColor = Color.White
        }

        Row(        modifier = modifier.fillMaxWidth()
            .clip(RoundedCornerShape(topStart = 12.dp, bottomStart = 12.dp))
            .background(Color.White)
            .clickable {
                onPokemonClick()
            }.padding(19.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween) {
            CircularAvatar(
                imageUrl = pokemon.sprites?.frontDefault,
                name = pokemon.name,
                textColor = textColor,
                fontSize = 30.sp,
                backgroundColor =  backgroundColor,
                size = 80.dp,
                onPokemonClickImage = {imageUrl ->
                    onPokemonClickImage(imageUrl)
                }
                )
            Text(text = pokemon.name.toString())

            if(pokemon.isFavorite == true){
                Image(modifier = Modifier.size(17.dp).clickable {
                  /*  val pokemonEdit : Pokemon = pokemon!!
                    pokemonEdit.isFavorite = !pokemonEdit.isFavorite
                    viewModel.onEvent(DetailEvent.onlikeOrDisLikePokemon(pokemonEdit))*/
                }, painter = painterResource(id = R.drawable.corazon_like), contentDescription = "like")
            }else{
                Image( painter = painterResource(id = R.drawable.corazon_unlike), contentDescription = "unlike", modifier = Modifier.size(17.dp).clickable {
                   /* val pokemonEdit : Pokemon = state!!.pokemon!!
                    pokemonEdit.isFavorite = !pokemonEdit.isFavorite
                    viewModel.onEvent(DetailEvent.onlikeOrDisLikePokemon(pokemonEdit))*/
                })
            }
        }
    }

}