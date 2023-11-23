package com.example.weatherapp.data.mappers

import com.example.weatherapp.data.remote.Daily
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
        val windSpeed = windSpeeds[index]
        val humidity = humidities[index]
//        val rain = rainSum[index]
        IndexedWeatherData(
            index = index,
            WeatherData(
                time = LocalDateTime.parse(time, DateTimeFormatter.ISO_DATE_TIME),
                temperatureCelsius = temperature,
                humidity = humidity,
                windSpeed = windSpeed,
                pressure = pressure,
                weatherType = WeatherType.fromWMO(weatherCode),
                rain = 0.0
            )
        )
    }.groupBy {
        it.index / 24
    }.mapValues { data ->
        data.value.map {
            it.weatherData
        }
    }
}

fun Daily.toDailyRain(): Map<Int, Double> {

    val rain = mutableMapOf<Int, Double>()
    var index = 0
    rainSum.map { data ->
        rain[index++] = data
    }
    return rain
}

data class DaysWeather(
    val minTemp: Double,
    val maxTemp: Double,
    val day: String,
    val weatherType: WeatherType,
)

sealed class Day(val dayNumber: String) {
    object Sunday : Day("Sunday")
    object Monday : Day("Monday")
    object Tuesday : Day("Tuesday")
    object Wednesday : Day("Wednesday")
    object Thursday : Day("Thursday")
    object Friday : Day("Friday")
    object Saturday : Day("Saturday")
}

fun toDaysWeather(weatherMap: Map<Int, List<WeatherData>>): Map<Int, DaysWeather> {
    val daysWeather: MutableMap<Int, DaysWeather> = mutableMapOf()

    weatherMap.forEach { listEntry ->
        val day: String = when (listEntry.key) {
            0 -> Day.Monday.dayNumber
            1 -> Day.Tuesday.dayNumber
            2 -> Day.Wednesday.dayNumber
            3 -> Day.Thursday.dayNumber
            4 -> Day.Friday.dayNumber
            5 -> Day.Saturday.dayNumber
            6 -> Day.Sunday.dayNumber
            else -> "Invalid Day"
        }
        daysWeather[listEntry.key] = DaysWeather(
            minTemp = listEntry.value.minOfOrNull { it.temperatureCelsius } ?: 0.0,
            maxTemp = listEntry.value.maxOfOrNull { it.temperatureCelsius } ?: 0.0,
            day = day,
            weatherType = listEntry.value[0].weatherType
        )
    }
    return daysWeather
}

fun WeatherDto.toWeatherInfo(): WeatherInfo {
    val weatherDateMap = weatherData.toWeatherDataMap()
    val rain = daily.toDailyRain()
    val now: LocalDateTime = LocalDateTime.now()
    val daysWeather = toDaysWeather(weatherDateMap)
    val currentWeatherData = weatherDateMap[0]?.find {
        val hour = if (now.minute < 30) now.hour else now.hour.plus(1)
        it.time.hour == hour
    }
    return WeatherInfo(
        currentWeatherData = currentWeatherData,
        weatherDatePerDay = weatherDateMap,
        rainAverage = rain,
        daysWeather = daysWeather
    )
}