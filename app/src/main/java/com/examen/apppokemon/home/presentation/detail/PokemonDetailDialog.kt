package com.examen.apppokemon.home.presentation.detail

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import com.examen.apppokemon.core.presentation.CircularAvatar

@Composable
fun DetailPokemonDialog(
    viewModel : PokemonDetailViewModel = hiltViewModel(),
    name: String
    , onDismiss: () -> Unit) {
    val state = viewModel.state
    LaunchedEffect(Unit){
        viewModel.getPokemonDetail(name)
    }
    var backgroundColor = Color.Yellow
    var textColor = Color.Green
    if (state.pokemon?.sprites?.frontDefault != null && state.pokemon!!.sprites?.frontDefault?.isNotEmpty() == true){
        backgroundColor = Color.White
        textColor = Color.White
    }
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            Column(modifier = Modifier
                .padding(20.dp),
                horizontalAlignment = Alignment.CenterHorizontally,) {
                CircularAvatar(
                    imageUrl = state.pokemon?.sprites?.frontDefault,
                    name = state.pokemon?.name,
                    textColor = textColor,
                    fontSize = 30.sp,
                    backgroundColor =  backgroundColor,
                    size = 50.dp

                )
                Text("name: "+state.pokemon?.name.toString())
                Text("height: "+state.pokemon?.height)
                Text("weight: "+state.pokemon?.weight)
                Text("types: "+state.pokemon?.types?.map { it.type?.name })
            }

        }
    }
}