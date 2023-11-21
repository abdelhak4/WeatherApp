package com.example.weatherapp.presentation

import android.annotation.SuppressLint
import android.icu.text.SimpleDateFormat
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshotFlow
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherInfo
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Calendar
import kotlin.math.roundToInt


@SuppressLint("SimpleDateFormat")
@Composable
fun TodayCard(weatherData: WeatherInfo?) {
    val time = LocalDateTime.now().toLocalTime().hour
    val lazyListState = rememberLazyListState(time)
    var lastScrollState by remember { mutableStateOf(false) }

    val formatter = SimpleDateFormat("MMM,dd")
    val currentDate = Calendar.getInstance().time
    val formattedDate = formatter.format(currentDate)

    weatherData?.weatherDatePerDay?.get(0).let { data ->
        Card(
            modifier = Modifier
                .requiredHeight(217.dp)
                .requiredWidth(343.dp)
                .clip(shape = RoundedCornerShape(20.dp))
                .clickable {
                    lastScrollState = true
                },
            colors = CardDefaults.cardColors(containerColor = Color(0xff001026).copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
                horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.SpaceEvenly
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = "Today",
                        textAlign = TextAlign.Center,
                        fontSize = 20.sp,
                        fontWeight = FontWeight(700),
                        modifier = Modifier.padding(start = 16.dp, top = 14.dp),
                        color = Color.White
                    )
//                    if (lazyListState.firstVisibleItemIndex != time)
                    Text(
                        text = formattedDate,
                        fontWeight = FontWeight(400),
                        fontSize = 18.sp,
                        modifier = Modifier.padding(end = 18.dp, top = 14.dp),
                        color = Color.White
                    )
                }
                LaunchedEffect(lastScrollState) {
                    snapshotFlow { lazyListState }.collect {
                        lazyListState.scrollToItem(time)
                        lastScrollState = false
                    }
                }
                LazyRow(
                    modifier = Modifier.padding(13.dp),
                    horizontalArrangement = Arrangement.spacedBy(13.dp),
                    state = lazyListState,
                ) {
                    items(
                        items = data ?: listOf(),
                    ) { weather ->
                        HourlyWeather(
                            currentWeatherData = weather,
                        )
                    }
                }
            }
        }
    }
}


@Composable
fun HourlyWeather(currentWeatherData: WeatherData) {
    val formattedTime = remember(currentWeatherData) {
        currentWeatherData.time.format(
            DateTimeFormatter.ofPattern("HH:mm")
        )
    }
    val isNow = LocalDateTime.now().toLocalTime().hour == currentWeatherData.time.hour
    Box(
        modifier = Modifier
            .width(70.dp)
            .height(155.dp)
            .border(
                width = if (isNow) 1.dp else 0.dp,
                color = if (isNow) Color(0xFF5096FF) else Color(0xff254659).copy(alpha = 0.3f),
                shape = RoundedCornerShape(size = 20.dp)
            ),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = currentWeatherData.temperatureCelsius.roundToInt().toString() + "°C",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                ),
                modifier = Modifier.padding(end = 18.dp, start = 14.dp)
            )
            Image(
                painter = painterResource(id = currentWeatherData.weatherType.iconRes),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(43.dp)
            )
            Text(
                text = formattedTime,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
    }
}