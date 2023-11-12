package com.example.weatherapp.data.remote

import com.squareup.moshi.Json
import retrofit2.http.Query


data class WeatherDto(
    @field:Json(name = "hourly")
    val weatherData: WeatherDataDto
)
