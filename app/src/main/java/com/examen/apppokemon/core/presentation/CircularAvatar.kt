package com.examen.apppokemon.core.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest


@Composable
fun CircularAvatar(
    imageUrl: String? = null,
    name: String? = null,
    placeholder: String = "N/A",
    size: Dp = 100.dp,
    fontSize: TextUnit = 24.sp,
    backgroundColor: Color = Color.Gray,
    textColor: Color = Color.White
    ){
    Box(modifier = Modifier
        .size(size)
        .border(width = 2.dp, color = textColor, shape = CircleShape)
        .background(backgroundColor, shape = CircleShape), contentAlignment = Alignment.Center){
        if (!imageUrl.isNullOrBlank()) {
            var isError by remember { mutableStateOf(false) }
            AsyncImage(

                contentDescription = null,
                modifier = Modifier.fillMaxSize().clip(CircleShape),
               model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                   .crossfade(true)
                   .build(),
                contentScale = ContentScale.Fit,
                onState  = {
                    if(it is AsyncImagePainter.State.Error){
                        isError = true
                    }
                }
            )

            if (isError) {
                NameInititials(name, fontSize, textColor)
            }
        }else{
            NameInititials(name, fontSize, textColor)
        }

    }
}

