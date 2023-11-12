package com.example.weatherapp.domain.weather

data class WeatherInfo(
    val weatherDatePerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData? // represents the Weather data for today
)
