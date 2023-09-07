package com.example.loosewaves

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loosewaves.ui.theme.LooseWavesTheme


@Composable
fun LocationSpotDetail(location: String, modifier: Modifier = Modifier) {
    val pingImage = painterResource(R.drawable.location)

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = pingImage,
            contentDescription = "location",
            modifier = modifier
                .width(28.dp)
                .height(28.dp)
        )
        Text(
            text = location,
            fontSize = 20.sp,
            fontStyle = FontStyle.Italic,
            fontWeight = FontWeight.Light,
            color = Color.White,
        )
    }
}

@Composable
fun SpotInfo(name: String, location: String, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .padding(20.dp)
            .clip(RoundedCornerShape(16.dp))
            .background(Color.Black.copy(alpha = 0.3f))
            .padding(8.dp)
    ) {
        Text(
            text = name,
            fontSize = 32.sp,
            color = Color.White,
            fontWeight = FontWeight.SemiBold,
            modifier = Modifier
                .padding(
                    bottom = 4.dp
                )
        )
        LocationSpotDetail(
            location = location
        )
    }
}

@Composable
fun SpotImage(imagePath: Int, name: String, location: String, modifier: Modifier = Modifier) {
    val image = painterResource(imagePath)

    Box(
        modifier = Modifier
            .padding(40.dp)
            .fillMaxWidth()

    ) {
        Box(
            modifier = Modifier
                .height(320.dp)
                .clip(RoundedCornerShape(20.dp)),

            contentAlignment = Alignment.BottomStart
        ) {
            Image(
                painter = image,
                contentDescription = "Picture of $name",
                modifier = Modifier
                    .fillMaxSize(),
                contentScale = ContentScale.Crop,
            )
            SpotInfo(
                name = name,
                location = location,
            )
        }
    }
}

@Composable
fun SpotPage(imagePath: Int, name: String, location: String, modifier: Modifier = Modifier) {
    Box(
        modifier = Modifier
            .background(
                brush = Brush.horizontalGradient(
                    colors = listOf(Color(0xFF1BE7BE), Color(0xFF3774FF)),
                    startX = 0f,
                    endX = Float.POSITIVE_INFINITY
                )
            )
    ) {
        SpotImage(
            imagePath = imagePath,
            name = name,
            location = location
        )
    }
}


@Preview(showBackground = true)
@Composable
fun SpotDetailPreview() {
    LooseWavesTheme {
        SpotPage(imagePath = R.drawable.thomas_ashlock_64485_unsplash, name = "Pipeline", location = "Oahu, Hawaii")
    }
}

