package com.examen.apppokemon.home.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import coil.compose.AsyncImagePainter
import coil.request.ImageRequest

@Composable
fun ExpandedImageDialog(
    imageUrl: String
    , onDismiss: () -> Unit) {
    Dialog(onDismissRequest = onDismiss) {
        Box(
            modifier = Modifier
                .background(Color.Transparent, shape = RoundedCornerShape(16.dp))
                .padding(16.dp)
        ) {
            AsyncImage(
                contentDescription = null,
                modifier = Modifier.fillMaxSize().clip(CircleShape),
                model = ImageRequest.Builder(LocalContext.current).data(imageUrl)
                    .crossfade(true)
                    .build(),
                contentScale = ContentScale.Fit,
            )
        }
    }
}