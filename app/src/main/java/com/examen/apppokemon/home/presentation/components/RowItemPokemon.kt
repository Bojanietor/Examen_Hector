package com.examen.apppokemon.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.examen.apppokemon.core.presentation.CircularAvatar
import com.examen.apppokemon.home.domain.models.Pokemon.Pokemon

@Composable
fun RowItemPokemon(
    pokemon: Pokemon,
    modifier: Modifier = Modifier,
    onPokemonClick: () -> Unit
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
            horizontalArrangement = Arrangement.spacedBy(10.dp)) {
            CircularAvatar(
                imageUrl = pokemon.sprites?.frontDefault,
                name = pokemon.name,
                textColor = textColor,
                fontSize = 30.sp,
                backgroundColor =  backgroundColor,
                size = 50.dp

                )
            Text(text = pokemon.name.toString())
        }
    }

}