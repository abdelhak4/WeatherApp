package com.example.weatherapp.domain.weather

import com.example.weatherapp.data.mappers.DaysWeather

data class WeatherInfo(
    val weatherDatePerDay: Map<Int, List<WeatherData>>,
    val currentWeatherData: WeatherData?, // represents the Weather data for today
    val rainAverage: Map<Int, Double>,
    val daysWeather: Map<Int, DaysWeather>
)
