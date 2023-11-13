package com.example.weatherapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
              WeatherScreen()
            }
        }
    }
}


@Composable
fun WeatherScreen() {
    Box (
//        color = Color(0xFF08244F),
        modifier = Modifier
            .requiredWidth(width = 423.dp)
            .requiredHeight(height = 858.dp)
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xff08244f),
                    0.3f to Color(0xff0f3f92),
                    0.47f to Color(0xff134cb5),
                    1f to Color(0xff0b42ab),
                    1f to Color(0xff0b42ab),
                    start = Offset.Zero,
                    end = Offset(100.0f, 100.0f)
                )
            )
    ) {
//        Text(text = "hello")
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherAppPrev() {
    WeatherScreen()
}
@Composable
fun GeneratedCode(modifier: Modifier = Modifier) {
    Box(
        modifier = modifier
            .requiredWidth(width = 423.dp)
            .requiredHeight(height = 858.dp)
            .clip(shape = RoundedCornerShape(40.dp))
            .background(brush = Brush.linearGradient(
                0f to Color(0xff08244f),
                0.3f to Color(0xff0f3f92),
                0.47f to Color(0xff134cb5),
                1f to Color(0xff0b42ab),
                1f to Color(0xff0b42ab),
                start = Offset(0f, 0f),
                end = Offset.Infinite)
            )
    )
}

@Preview(widthDp = 423, heightDp = 858)
@Composable
private fun GeneratedCodePreview() {
    GeneratedCode(Modifier)
}