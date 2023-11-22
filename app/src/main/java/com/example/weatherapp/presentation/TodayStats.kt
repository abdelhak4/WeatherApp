package com.example.weatherapp.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Frame(
    text: String,
    id: Int,
) {
    Row(
        horizontalArrangement = Arrangement.spacedBy(1.dp), verticalAlignment = Alignment.CenterVertically
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