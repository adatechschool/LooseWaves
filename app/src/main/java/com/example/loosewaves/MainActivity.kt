@file:OptIn(ExperimentalFoundationApi::class)

package com.example.loosewaves

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
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
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
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
        composable("PageSpotList") { PageSpotList(navController = navController) }
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
fun Login(navController: NavController? = null) {
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
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(top = 37.dp),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            Text(
                text = "LooseWaves",
                style = TextStyle(
                    fontSize = 32.sp,
                    fontWeight = FontWeight(700),
                    color = Color(0xFFFFFFFF),
                )
            )
            Spacer(modifier = Modifier.height(35.dp))
            Box(
                modifier = Modifier
                    .background(color = Color(0xFFF5F5F5), shape = RoundedCornerShape(size = 50.dp))
                    .fillMaxHeight()
            ) {
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .offset(y = 29.dp),
                        verticalArrangement = Arrangement.spacedBy(383.dp, Alignment.Top),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(modifier = Modifier
                            .width(121.dp)
                            .height(28.dp),
                            text = "Let’s Surf !",
                            style = TextStyle(
                                fontSize = 24.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF303030),
                            )
                        )
                        Text(
                            modifier = Modifier
                                .width(195.dp)
                                .height(21.dp),
                            text = "Pas de compte ?  Inscris toi",
                            style = TextStyle(
                                fontSize = 15.sp,
                                lineHeight = 21.sp,
                                fontWeight = FontWeight(700),
                                color = Color(0xFF1E232C),
                                letterSpacing = 0.15.sp,
                            )
                        )
                    }
                }
            }
        }
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

        Button(onClick = { navController?.navigate("PageSpotList") }) {
            Text("Démarrer")
        }
    }
}

@Composable
fun PageSpotList(navController: NavController? = null) {
    ListOfSpotsPage()
}

@Preview(showBackground = true)
@Composable
fun Preview() {
    LooseWavesTheme {
        Page1()
    }
}