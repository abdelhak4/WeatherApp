package com.example.weatherapp


import android.Manifest
import android.content.res.Configuration
import android.graphics.fonts.Font
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterVertically
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.presentation.WeatherState
import com.example.weatherapp.presentation.WeatherViewModel
import com.example.weatherapp.presentation.ui.theme.WeatherAppTheme
import dagger.hilt.android.AndroidEntryPoint
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.UiMode
import com.example.weatherapp.presentation.TodayCard

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: WeatherViewModel by viewModels()
    private lateinit var permissionLauncher: ActivityResultLauncher<Array<String>>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        permissionLauncher = registerForActivityResult(
            ActivityResultContracts.RequestMultiplePermissions()
        ) {
            viewModel.loadWeatherInfo()
            println(viewModel.state)
        }

        permissionLauncher.launch(
            arrayOf(
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,

                )
        )
        setContent {
            WeatherAppTheme {
                // A surface container using the 'background' color from the theme
                WeatherScreen(state = viewModel.state)
                if (viewModel.state.error != null) {
                    Box(contentAlignment = Alignment.Center) {
                        Text(text = viewModel.state.error!!)
                    }
                }
            }
        }
    }
}


@Composable
fun WeatherScreen(
    state: WeatherState,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xff08244f),
                    0.3f to Color(0xff0f3f92),
                    0.47f to Color(0xff134cb5),
                    1f to Color(0xff0b42ab),
                    1f to Color(0xff0b42ab),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )
    ) {
        state.weatherInfo?.currentWeatherData?.let { data ->
            Column(
                modifier = Modifier,
                horizontalAlignment = CenterHorizontally,
//                verticalArrangement = Arrangement.Center
            ) {
                Image(
                    painter = painterResource(id = data.weatherType.iconRes),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .width(200.dp)
                        .padding(24.dp)
                )

                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "${data.temperatureCelsius}°C",
                    fontSize = 60.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
                    modifier = Modifier.padding(80.dp)
                )
                Spacer(modifier = Modifier.height(16.dp))

//                Box(
//                    modifier = Modifier
//                        .clip(RoundedCornerShape(12.dp))
//                ) {
//
//                }

            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun WeatherAppPrev() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(
                brush = Brush.linearGradient(
                    0f to Color(0xff08244f),
                    0.3f to Color(0xff0f3f92),
                    0.47f to Color(0xff134cb5),
                    1f to Color(0xff0b42ab),
                    1f to Color(0xff0b42ab),
                    start = Offset(0f, 0f),
                    end = Offset.Infinite
                )
            )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = CenterHorizontally,
//                verticalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = R.drawable.ic_cloudy),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .width(200.dp)
                    .padding(top = 59.dp)
//                    .shadow(
//                        elevation = 30.dp,
//                        spotColor = Color(0x0D000000),
//                        ambientColor = Color(0x0D000000)
//                    )
            )

//            Spacer(modifier = Modifier.height(16.dp))
            Text(
                text = "20°",
                fontSize = 60.sp,
                color = Color.White,
                fontWeight = FontWeight(600),
                textAlign = TextAlign.Center,
//                modifier = Modifier.padding(80.dp)
            )
            Spacer(modifier = Modifier.height(5.dp))

//                modifier = Modifier.padding(80.dp)
            Text(
                text = "Precipitations",
                fontSize = 18.sp,
                color = Color.White,
                fontWeight = FontWeight(400),
                textAlign = TextAlign.Center,
            )
            Spacer(modifier = Modifier.height(24.dp))
            Box(
                modifier = Modifier
                    .width(343.dp)
                    .height(47.dp)
                    .background(
                        color = Color(0xff001026).copy(alpha = 0.3f),
                        shape = RoundedCornerShape(size = 20.dp)
                    )
            ) {
                Row(
                    horizontalArrangement = Arrangement.SpaceAround,
                    verticalAlignment = CenterVertically,
                    modifier = Modifier.fillMaxSize()
                ) {
                    Frame(text = "6%", id = R.drawable.rain)
                    Frame(text = "90%", id = R.drawable.humidity)
                    Frame(text = "19 km/h", id = R.drawable.wind)
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            TodayCard()
            Spacer(modifier = Modifier.weight(1f))
        }
    }
}

@Composable
fun Frame(
    text: String,
    id: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(1.dp), verticalAlignment = CenterVertically
    ) {
        Image(painter = painterResource(id = id), contentDescription = null)
        Spacer(modifier = Modifier.padding(start = 2.dp))
        Text(
            text = text,
            fontWeight = FontWeight(700),
            color = Color.White,
            fontSize = 14.sp,
            fontFamily = FontFamily.SansSerif
        )
    }
}


//
//@Composable
//fun NextForcast(state: WeatherState) {
//    Card(
//        modifier = Modifier
//            .requiredHeight(217.dp)
//            .requiredWidth(343.dp)
//            .clip(shape = RoundedCornerShape(20.dp)),
//        colors = CardDefaults.cardColors(containerColor = Color(0xff001026).copy(alpha = 0.3f))
//    ) {
//        Column(
//            modifier = Modifier
//        ) {
//            Row {
//                Text(
//                    text = "Next Forecast",
//                    style = androidx.compose.ui.text.TextStyle(
//                        fontSize = 20.sp,
//                        fontWeight = FontWeight(700),
//                        color = Color(0xFFFFFFFF),
//                    )
//                )
//                Image(
//                    imageVector = Icons.Rounded.LocationOn,
//                    contentDescription = "image description",
//                    contentScale = ContentScale.None
//                )
//            }
//            LazyRow() {
//                items(items = state.weatherInfo?.weatherDatePerDay) {
//
//                }
//            }
//        }
//    }
//}
//
//@Composable
//fun DayForecast(
//    id: Int,
//    day: String,
////    state: WeatherState
//) {
//    Text(
//        text = "Monday",
//        style = TextStyle(
//            fontSize = 18.sp,
//            fontWeight = FontWeight(700),
//            color = Color(0xFFFFFFFF),
//        )
//    )
//
//    Image(
//        painter = painterResource(id = R.drawable.rain),
//        contentDescription = "image description",
//        contentScale = ContentScale.Crop
//    )
//
//    Text(
//        text = "13",
//        style = TextStyle(
//            fontSize = 18.sp,
//            fontWeight = FontWeight(500),
//            color = Color(0xFFFFFFFF),
//            textAlign = TextAlign.Center,
//        )
//    )
//}