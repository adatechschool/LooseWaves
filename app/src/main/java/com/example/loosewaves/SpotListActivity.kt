package com.example.loosewaves

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import com.example.loosewaves.ui.theme.LooseWavesTheme

class SpotListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
}

@Composable
fun Spot(imagePath: Int, name: String, location: String, modifier: Modifier = Modifier) {
    val image = painterResource(imagePath)

    Image(
        painter = image,
        contentDescription = "Spot image of $name",
    )
    Text(
        text = name,
    )
    Text(
        text = location,
    )
}

@Composable
fun ListOfSpots(modifier: Modifier = Modifier) {

}

@Preview(showBackground = true)
@Composable
fun SpotsListPreview() {
    LooseWavesTheme {

    }
}
