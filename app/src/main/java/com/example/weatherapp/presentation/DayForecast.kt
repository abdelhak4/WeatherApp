package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.weatherapp.R


@Composable
fun TodayCard() {
    Card(
        modifier = Modifier
            .requiredHeight(217.dp)
            .requiredWidth(343.dp)
            .clip(shape = RoundedCornerShape(20.dp)),
        colors = CardDefaults.cardColors(containerColor = Color(0xff001026).copy(alpha = 0.3f))
    ) {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.Start,
//            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Today",
                    textAlign = TextAlign.Center,
                    fontSize = 20.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(start = 16.dp, top = 14.dp),
                    color = Color.White
                )
                Text(
                    text = "Mar,9",
                    fontWeight = FontWeight(400),
                    fontSize = 18.sp,
                    modifier = Modifier.padding(end = 18.dp, top = 14.dp),
                    color = Color.White
                )
            }
            Row(
                modifier = Modifier.padding(13.dp),
                horizontalArrangement = Arrangement.spacedBy(13.dp)
            ) {
                HourlyWeather()
                HourlyWeather(true)
                HourlyWeather()
                HourlyWeather()

            }
        }
    }
}


@Composable
fun HourlyWeather(
    isNow: Boolean = false,

    ) {
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
                text = "24°C",
                style = TextStyle(
                    fontSize = 16.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                ),
                modifier = Modifier.padding(end = 18.dp, start = 14.dp)
            )
            Image(
                painter = painterResource(id = R.drawable.ic_heavysnow),
                contentDescription = null,
                contentScale = ContentScale.Inside,
                modifier = Modifier.size(43.dp)
            )
            Text(
                text = "16.00",
                style = TextStyle(
                    fontSize = 18.sp,
                    fontWeight = FontWeight(400),
                    color = Color(0xFFFFFFFF),
                )
            )
        }
    }
}