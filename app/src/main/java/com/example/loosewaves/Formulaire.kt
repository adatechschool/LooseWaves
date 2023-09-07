package com.example.loosewaves

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Formulaire(alias: String) {
    val state = remember {
        mutableStateOf(TextFieldValue(selection = TextRange(alias.length)))
    }

    TextField(
        value = state.value,
        placeholder = {
            Text(text = alias,
                style = TextStyle (
                    color = Color.Gray,
                ))
        },
        onValueChange = { text -> state.value = text },
        colors = TextFieldDefaults.outlinedTextFieldColors(
            containerColor = Color.White,
            textColor = Color(0xFF8391A1), // Couleur du texte
        ),
        shape = RoundedCornerShape(17.dp) // Valeur dp pour les coins arrondis
    )
}

