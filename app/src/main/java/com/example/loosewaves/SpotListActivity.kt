package com.example.loosewaves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.navigation.NavController
import com.example.loosewaves.ui.theme.LooseWavesTheme

class SpotListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun LocationSpotList(location: String, modifier: Modifier = Modifier) {
    val pingImage = painterResource(R.drawable.location)

    Row(
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = pingImage,
            contentDescription = "location",
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
        LocationSpotList(location = location)
    }
}

@Composable
fun Spot(
    imagePath: Int,
    name: String,
    location: String,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val image = painterResource(imagePath)

    Button(
        onClick = {
            navController?.navigate("SpotPage/${imagePath}/${name}/${location}")
        },
        colors = ButtonDefaults.buttonColors(
            containerColor = Color.White,
            contentColor = Color.Black
        ),
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    start = 20.dp,
                    top = 8.dp,
                    bottom = 8.dp
                )
        ) {
            Image(
                painter = image,
                contentDescription = "Spot image of $name",
                modifier = Modifier
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
}

@Composable
fun ListOfSpots(navController: NavController? = null, modifier: Modifier = Modifier) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Spot(
            imagePath = R.drawable.thomas_ashlock_64485_unsplash,
            name = "Pipeline",
            location = "Oahu, Hawaii",
            navController = navController
        )
        Spot(
            imagePath = R.drawable.jeremy_bishop_80371_unsplash,
            name = "Superbank",
            location = "Gold Coast, Australia"
        )
    }
}

@Composable
fun ListOfSpotsPage(navController: NavController? = null, modifier: Modifier = Modifier) {
    Column(
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
        Text(
            text = "The Best Surf Spots \nNext To You",
            color = Color.White,
            fontWeight = FontWeight.Bold,
            fontSize = 30.sp,
            lineHeight = 34.sp,
            modifier = Modifier
                .padding(
                    start = 16.dp,
                    top = 16.dp,
                )
        )
        ListOfSpots()
    }
}

@Preview(showBackground = true)
@Composable
fun SpotsListPreview() {
    LooseWavesTheme {
        ListOfSpotsPage()
    }
}
