package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredHeight
import androidx.compose.foundation.layout.requiredWidth
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R

@Preview(showSystemUi = true)
@Composable
fun NextForecast() {
    Card(
        modifier = Modifier
            .requiredHeight(217.dp)
            .requiredWidth(343.dp)
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
            DayForecast(1, "s")
        }
    }
}


@Composable
fun DayForecast(
    id: Int,
    day: String,
//    state: WeatherState
) {
    Row(
        modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(
            text = "Monday",
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                fontFamily = FontFamily(Font(R.font.alegreya_sans))

            ),
            modifier = Modifier.padding(start = 14.dp)
        )

        Image(
            painter = painterResource(id = R.drawable.rain),
            contentDescription = "image description",
            contentScale = ContentScale.Crop
        )
        TextField(
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
                fontFamily = FontFamily(Font(R.font.alegreya_sans))
            ),
            textStyle2 = TextStyle(
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.alegreya_sans)),
                fontWeight = FontWeight(700),
                color = Color(0xFFFFFFFF),
            )
        )
    }
}


@Composable
fun TextField(modifier: Modifier = Modifier, style: TextStyle, textStyle2: TextStyle) {
    Box(
        modifier = Modifier
            .requiredWidth(width = 23.dp)
            .requiredHeight(height = 22.dp)
    ) {
        Text(
            text = "21",
            color = Color.White,
            textAlign = TextAlign.Center,
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
                    x = 15.dp,
                    y = 4.dp
                )
                .padding(end = 12.dp)
        )
    }
    Box(
        modifier = Modifier
            .offset(
                x = 33.dp,
                y = 0.dp
            )
            .requiredWidth(width = 23.dp)
            .requiredHeight(height = 22.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        Text(
            text = "18",
            color = Color.White.copy(alpha = 0.5f),
            textAlign = TextAlign.Center,
            style = TextStyle(
                fontSize = 18.sp,
                fontWeight = FontWeight.Medium
            )
        )
        Text(
            text = "°C",
            color = Color.White.copy(alpha = 0.5f),
            style = TextStyle(
                fontSize = 10.sp,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier
                .align(alignment = Alignment.TopStart)
                .offset(
                    x = 15.dp,
                    y = 4.dp
                )
        )
    }
}
