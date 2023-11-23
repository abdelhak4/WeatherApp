package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.LocationOn
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R
import com.example.weatherapp.data.mappers.DaysWeather
import com.example.weatherapp.domain.weather.WeatherInfo

//@Preview(showSystemUi = true)
@Composable
fun NextForecast(weatherInfo: WeatherInfo?) {
    weatherInfo?.daysWeather.let { data ->
        Card(
            modifier = Modifier
                .requiredHeight(217.dp)
                .requiredWidth(343.dp)
                .offset(
                    x = 4.dp,
                    y = 24.dp
                )
                .clip(
                    shape = RoundedCornerShape(20.dp)
                ),
            colors = CardDefaults.cardColors(containerColor = Color(0xff001026).copy(alpha = 0.3f))
        ) {
            Column(
                modifier = Modifier.fillMaxSize(),
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(11.dp)
                ) {
                    Text(
                        text = "Next Forecast", style = TextStyle(
                            fontSize = 20.sp,
                            fontWeight = FontWeight(700),
                            color = Color(0xFFFFFFFF),
                        )
                    )
                    Image(
                        imageVector = Icons.Rounded.LocationOn,
                        contentDescription = "image description",
                        contentScale = ContentScale.None
                    )
                }
                LazyColumn(
                    modifier = Modifier,
                ) {
                    items(
                        items = data?.keys!!.toList(),
                    ) { weatherInfo ->
                        DayForecast(data = data[weatherInfo])
                    }
                }
            }
        }
    }
}


@Composable
fun DayForecast(
    data: DaysWeather?,
) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = data!!.day,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                fontFamily = FontFamily(Font(R.font.alegreya_sans))

            ),
            modifier = Modifier
                .width(95.dp)
                .height(22.dp)
                .padding(start = 14.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.rain),
            contentDescription = "image description",
            contentScale = ContentScale.Crop
        )
        TextField()
    }
}


@Composable
fun TextField() {
    Row(
        modifier = Modifier.padding(end = 12.dp)
    ) {

        Box(
            modifier = Modifier
                .requiredWidth(width = 30.dp)
                .requiredHeight(height = 30.dp)
        ) {
            Text(
                text = "21",
                color = Color.White,
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium
                )
            )
            Text(
                text = "°C",
                color = Color.White,
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 18.dp,
                        y = 3.dp
                    )
            )
        }
        /* this code is redundant it can be simplified */
        Spacer(modifier = Modifier.width(5.dp))
        Box(
            modifier = Modifier
                .requiredWidth(width = 30.dp)
                .requiredHeight(height = 30.dp)
        ) {
            Text(
                text = "21",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0x80FFFFFF),
                )
            )
            Text(
                text = "°C",
                style = TextStyle(
                    fontSize = 10.sp,
                    fontWeight = FontWeight.Medium,
                    color = Color(0x80FFFFFF),
                ),
                modifier = Modifier
                    .align(alignment = Alignment.TopStart)
                    .offset(
                        x = 18.dp,
                        y = 3.dp
                    )
                    .padding(end = 12.dp)
            )
        }
    }
}
