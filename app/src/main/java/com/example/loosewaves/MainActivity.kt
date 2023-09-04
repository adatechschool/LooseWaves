package com.example.loosewaves

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loosewaves.ui.theme.LooseWavesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LooseWavesTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                   Page1()
                }
            }
        }
    }
}

@Composable
fun Page1(modifier: Modifier = Modifier) {
    val imageModifier = Modifier
        .fillMaxWidth()
        .padding(0.dp)
    Image(
        painter = painterResource(id = R.drawable.img_page_2),
        contentDescription = "image description",
        contentScale = ContentScale.FillBounds,
        modifier = imageModifier
    )
    Text(
        text = "LooseWaves",
        style = TextStyle(
            fontSize = 55.sp,
            fontWeight = FontWeight(500),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
//            .background(Color.White)
    )
}



@Preview(showBackground = true)
@Composable
fun Preview() {
    LooseWavesTheme {
        Page1()
    }
}