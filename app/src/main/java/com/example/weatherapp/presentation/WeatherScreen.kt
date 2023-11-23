package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


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
        state.weatherInfo?.let { data ->
            Column(
                modifier = Modifier.fillMaxSize() ,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = data.currentWeatherData?.weatherType?.iconRes!!),
                    contentDescription = null,
                    contentScale = ContentScale.Inside,
                    modifier = Modifier
                        .width(200.dp)
                        .padding(top = 59.dp)
                )
                Text(
                    text = "${data.currentWeatherData.temperatureCelsius}°",
                    fontSize = 60.sp,
                    color = Color.White,
                    fontWeight = FontWeight(600),
                    textAlign = TextAlign.Center,
//                modifier = Modifier.padding(80.dp)
                )
                Spacer(modifier = Modifier.height(5.dp))

//                modifier = Modifier.padding(80.dp)
                Text(
                    text = data.currentWeatherData.weatherType.weatherDesc,
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
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier.fillMaxSize()
                    ) {
                        Frame(
                            text = "${data.rainAverage[0]}%",
                            id = R.drawable.rain
                        )
                        Frame(
                            text = "${data.currentWeatherData.humidity}%",
                            id = R.drawable.humidity
                        )
                        Frame(
                            text = "${data.currentWeatherData.windSpeed} km/h",
                            id = R.drawable.wind
                        )
                    }
                }
                Spacer(modifier = Modifier.height(20.dp))
                TodayCard(state.weatherInfo)
                Spacer(modifier = Modifier.padding(top = 25.dp))
                NextForecast(state.weatherInfo)
            }
        }

    }

}