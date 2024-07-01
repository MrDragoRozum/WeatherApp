package ru.rozum.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.domain.entity.Weather

interface WeatherRepository {
    suspend fun getWeather(cityId: Int): Weather
    suspend fun getForecast(cityId: Int): Weather
}