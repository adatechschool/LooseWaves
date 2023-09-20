@file:OptIn(ExperimentalFoundationApi::class)

package com.example.loosewaves

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.Button
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.loosewaves.ui.theme.LooseWavesTheme
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET

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
                   NavigationView()
                }
            }
        }
    }
}

@Composable
fun NavigationView() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "LaunchScreen") {
        composable("LaunchScreen") { LaunchScreen(navController = navController) }
        composable("Login") { Login(navController = navController) }
        composable("ListOfSpotsPage") { SurfSpotListScreen(navController = navController) }
        composable(
            route = "SpotPage/{imagePath}/{name}/{location}/{difficulty}/{surfBreak}",
            arguments = listOf(
                navArgument("imagePath") { type = NavType.IntType },
                navArgument("name") { type = NavType.StringType },
                navArgument("location") { type = NavType.StringType },
                navArgument("difficulty") { type = NavType.IntType },
                navArgument("surfBreak") { type = NavType.StringType },
            )
        ) { backStackEntry ->
            val imagePath = backStackEntry.arguments?.getInt("imagePath")
            val name = backStackEntry.arguments?.getString("name")
            val location = backStackEntry.arguments?.getString("location")
            val difficulty = backStackEntry.arguments?.getInt("difficulty")
            val surfBreak = backStackEntry.arguments?.getString("surfBreak")
            if (imagePath != null && name != null && location != null && difficulty != null && surfBreak != null) {
                SpotPage(
                    imagePath = imagePath,
                    name = name,
                    location = location,
                    difficulty = difficulty,
                    surfBreak = surfBreak,
                    navController = navController
                )
            } else {
                // Handle error case
            }
        }
    }
}


@Composable
fun LaunchScreen(navController: NavController? = null) {
    val pagerState = rememberPagerState(initialPage = 0)
    HorizontalPager(pageCount = 2, state = pagerState) { page ->
        when (page) {
            0 -> Page1()
            1 -> Page2(navController)
        }
    }

    Text(
        text = "LooseWaves",
        color = if (pagerState.currentPage == 0) Color.Black else Color.White,
        style = TextStyle(
            fontSize = 55.sp,
            fontWeight = FontWeight(500),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}


@Composable
fun Page1() {
    Box(modifier = Modifier.fillMaxSize()) {
        Image(
            painter = painterResource(id = R.drawable.img_page_1),
            contentDescription = "image description",
            contentScale = ContentScale.FillBounds,
            modifier = Modifier
                .fillMaxSize()
        )
    }
}


@Composable
fun Page2(navController: NavController? = null) {
    Box(modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter) {
        Image(
            painter = painterResource(id = R.drawable.img_page_2),
            contentDescription = "Image page 2",
            contentScale = ContentScale.FillBounds
        )

        Button(onClick = { navController?.navigate("Login") }) {
            Text("Démarrer")
        }
    }
}

@Composable
fun PageSpotList(navController: NavController? = null) {
    SurfSpotListScreen()
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LooseWavesTheme {
        Page1()
    }
}