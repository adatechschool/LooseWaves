package com.example.loosewaves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.foundation.lazy.items
import com.example.loosewaves.Api.retrofitService

class SpotListActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SurfSpotListScreen()
        }
    }
}

private const val BASE_URL = "http://10.0.2.2:3000/"

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create())
    .baseUrl(BASE_URL)
    .build()

interface ApiService {
    @GET("surfspots")
    suspend fun getSurfSpots(): List<SurfSpot> {
        try {
            val response = retrofitService.getSurfSpots()
            Log.d("API Response", "Response: $response")
            return response
        } catch (e: Exception) {
            Log.e("API Error", "Error: ${e.message}")
            throw e
        }
    }
}

object Api {
    val retrofitService: ApiService by lazy { retrofit.create(ApiService::class.java) }
}

data class SurfSpot(
    var id: String,
    var name: String,
    var destination: String,
    var difficulty: Int,
    var surfBreak: String,
    var seasonBegin: String,
    var seasonEnd: String
)

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
            fontSize = 16.sp,
            fontStyle = FontStyle.Italic
        )
    }
}

@Composable
fun DifficultyImage(difficulty: Int, modifier: Modifier = Modifier) {
    Row(modifier = modifier) {
        for (i in 1..difficulty) {
            Image(
                painter = painterResource(R.drawable.wave),
                contentDescription = "Wave for difficulty level $i",
                modifier = Modifier
                    .size(24.dp) // Adjust the size as needed
                    .padding(end = 4.dp) // Add some space between the images
            )
        }
    }
}

@Composable
fun DifficultyLevel(difficulty: Int, modifier: Modifier = Modifier) {
    Column {
        Text(
            text = "Difficulty level:",
            fontWeight = FontWeight.SemiBold,
            fontSize = 20.sp
        )
        DifficultyImage(difficulty = difficulty)
    }
}

@Composable
fun SpotInformation(name: String, location: String, difficulty: Int, modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .padding(start = 20.dp)
    ) {
        Text(
            text = name,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            modifier = modifier
                .padding(
                    bottom = 6.dp
                )
        )
        LocationSpotList(location = location)
        DifficultyLevel(difficulty = difficulty)
    }
}

@Composable
fun Spot(
    imagePath: Int = R.drawable.thomas_ashlock_64485_unsplash,
    name: String,
    location: String,
    difficulty: Int,
    surfBreak: String,
    navController: NavController? = null,
    modifier: Modifier = Modifier
) {
    val image = painterResource(imagePath)

    Button(
        onClick = {
            navController?.navigate("SpotPage/${imagePath}/${name}/${location}/${difficulty}/${surfBreak}")
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
                difficulty = difficulty,
            )
        }
    }
}

@Composable
fun ListOfSpots(
    surfSpots: List<SurfSpot>, 
    navController: NavController? = null,
    modifier: Modifier = Modifier
    ) {
    LazyColumn {
        items(surfSpots) { spot -> // use items function that takes a single list argument
            Spot(
                name = spot.name,
                location = spot.destination,
                difficulty = spot.difficulty,
                surfBreak = spot.surfBreak,
                navController = navController,
            )
        }
    }
}

@Composable
fun ListOfSpotsPage(
    surfSpots: List<SurfSpot>, 
    navController: NavController? = null, 
    modifier: Modifier = Modifier
    ) {
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
        ListOfSpots(surfSpots, navController)
    }
}

@Composable
fun SurfSpotListScreen(navController: NavController? = null,) {
    val surfSpots = remember { mutableStateOf(listOf<SurfSpot>()) }

    LaunchedEffect(Unit) {
        surfSpots.value = Api.retrofitService.getSurfSpots()
    }

    LooseWavesTheme {
        ListOfSpotsPage(surfSpots.value, navController)
    }
}

@Preview(showBackground = true)
@Composable
fun SpotsListPreview() {
    LooseWavesTheme {
        SurfSpotListScreen()
    }
}
