package com.example.weatherapp.data.remote

import com.squareup.moshi.Json


data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto,
    val daily: Daily,
)

data class Daily (
    @field:Json(name = "rain_sum")
    val rainSum: List<Double>,
)
