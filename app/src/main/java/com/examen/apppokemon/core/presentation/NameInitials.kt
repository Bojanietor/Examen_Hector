package com.examen.apppokemon.core.presentation

import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.TextUnit

@Composable
fun NameInititials(name : String?, fontSize: TextUnit, textColor: Color){
    val initials = remember(name){
        name?.split( " ")
            ?.take(2)
            ?.joinToString("") { it.take(1).uppercase() }
            ?: ""
    }
    Text(text = if (initials.isNotEmpty()) initials else "N/A",
        style =  TextStyle(
            color =textColor,
            fontSize = fontSize,
            fontWeight = FontWeight.Bold,
            textAlign =  TextAlign.Center
        ),
        modifier = Modifier.wrapContentSize()
    )
}