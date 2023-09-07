package com.example.loosewaves

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

@Composable
fun Register(navController: NavController? = null) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF1BE7BE), Color(0xFF3774FF)),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            )
    ) {
        // Contenu de la boîte principale
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 37.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            // Titre "LooseWaves"
            Text(
                text = "LooseWaves",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    color = Color.White,
                )
            )
            Spacer(modifier = Modifier.height(40.dp))

            // Boîte blanche avec coins arrondis
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(size = 50.dp))
                    .fillMaxWidth()
            ) {
                // Contenu de la boîte blanche
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 29.dp, horizontal = 16.dp),
                    verticalArrangement = Arrangement.spacedBy(16.dp, Alignment.Top),
                    horizontalAlignment = Alignment.CenterHorizontally,
                ) {
                    Text(
                        modifier = Modifier
                            .width(121.dp)
                            .height(28.dp),
                        text = "Let’s Surf !",
                        style = TextStyle(
                            fontSize = 24.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFF303030),
                        )
                    )
                    Formulaire("Nom")
                    Formulaire("Email")
                    Formulaire("Mot de passe")
                    Formulaire("Confirmer le mot de passe")


                    Button(
                        onClick = { navController?.navigate("PageSpotList") },
                        modifier = Modifier
                            .fillMaxWidth(),
                        colors = ButtonDefaults.buttonColors(
                            containerColor = Color(0xFF3774FF),
                            contentColor = Color.White
                        )
                    ) {
                        Text("Register")
                    }
                }
            }
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(30.dp),
                horizontalAlignment = Alignment.CenterHorizontally,

                ) {
                Text(
                    text = "Déjà un compte ?",
                    style = TextStyle(
                        fontSize = 15.sp,
                        lineHeight = 21.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFFFFFFFF),
                        letterSpacing = 0.15.sp,
                    )
                )
                Spacer(modifier = Modifier.height(15.dp))
                Button(
                    onClick = { navController?.navigate("Login") },
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color(0xFF3774FF)
                    )
                ) {
                    Text(
                        text = "Connexion",
                        style = TextStyle(
                            fontSize = 15.sp,
                            fontWeight = FontWeight(700),
                        )
                    )
                }
            }
        }
    }
}