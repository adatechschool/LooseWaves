package com.example.loosewaves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.loosewaves.ui.theme.LooseWavesTheme

class SpotListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun Location(location: String, modifier: Modifier = Modifier) {
    val pingImage = painterResource(R.drawable.location)

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = pingImage,
            contentDescription = "ping",
            modifier = modifier
                .width(16.dp)
                .height(16.dp)
        )
        Text(
            text = location,
            fontSize = 12.sp,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun SpotInformation(name: String, location: String, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(start = 20.dp)
    ) {
        Text(
            text = name,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(
                    bottom = 4.dp
                )
        )
        Location(location = location)
    }
}

@Composable
fun Spot(imagePath: Int, name: String, location: String, modifier: Modifier = Modifier) {
    val image = painterResource(imagePath)

    Row(

        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(color = Color.Red)
            .padding(16.dp)
    ) {
        Image(
            painter = image,
            contentDescription = "Spot image of $name",
            modifier = modifier
                .width(120.dp)
                .height(132.dp)
                .clip(RoundedCornerShape(20.dp)),
            contentScale = ContentScale.Crop,
        )
        SpotInformation(
            name = name,
            location = location,
        )
    }
}

@Composable
fun ListOfSpots(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun SpotsListPreview() {
    LooseWavesTheme {
        Spot(imagePath = R.drawable.thomas_ashlock_64485_unsplash, name = "Pipeline", location = "Oahu, Hawaii")
    }
}
