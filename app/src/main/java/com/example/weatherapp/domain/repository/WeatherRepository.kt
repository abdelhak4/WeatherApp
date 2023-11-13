package com.example.weatherapp.domain.repository

import com.example.weatherapp.domain.utils.Resource
import com.example.weatherapp.domain.weather.WeatherInfo

interface WeatherRepository {
    suspend fun getWeatherData(lat: Double, lang: Double): Resource<WeatherInfo>
}