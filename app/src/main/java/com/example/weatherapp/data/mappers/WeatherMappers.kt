package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.WeatherDataDto
import com.example.weatherapp.data.remote.WeatherDto
import com.example.weatherapp.domain.weather.WeatherData
import com.example.weatherapp.domain.weather.WeatherInfo
import com.example.weatherapp.domain.weather.WeatherType
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

private data class IndexedWeatherData(
    val index: Int,
    val weatherData: WeatherData,
)


fun WeatherDataDto.toWeatherDataMap(): Map<Int, List<WeatherData>> {
    return time.mapIndexed { index, time ->
        val temperature = temperatures[index]
        val weatherCode = weatherCodes[index]
        val pressure = pressures[index]
        val windSpeed = windSpeed[index]
        val humidity = humidities[index]
        IndexedWeatherData(
            index = index,
            WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                humidity = humidity,
                windSpeed = windSpeed,
                pressure = pressure,
                weatherType = WeatherType.fromWMO(weatherCode)
            )
        )
    }.groupBy {
        it.index
    }.mapValues { data ->
        data.value.map {
            it.weatherData
        }
    }
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDateMap = weatherData.toWeatherDataMap()
    val now: LocalDateTime = LocalDateTime.now()
    val currentWeatherData = weatherDateMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour.plus(1)
        it.time.hour == hour
    }
    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        weatherDatePerDay = weatherDateMap
    )
}