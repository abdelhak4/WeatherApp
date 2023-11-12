package com.example.weatherapp.domain.weather

import java.time.LocalDateTime

data class WeatherData(
    val time: LocalDateTime,
    val temperatureCelsius: Double,
    val humidity: Double,
    val windSpeed: Double,
    val pressure: Double,
    val weatherType: WeatherType
)
