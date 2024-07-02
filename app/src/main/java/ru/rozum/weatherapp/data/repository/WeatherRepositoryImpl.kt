package ru.rozum.weatherapp.data.repository

import ru.rozum.weatherapp.data.mapper.toEntity
import ru.rozum.weatherapp.data.network.api.ApiService
import ru.rozum.weatherapp.domain.entity.Forecast
import ru.rozum.weatherapp.domain.entity.Weather
import ru.rozum.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class WeatherRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : WeatherRepository {
    override suspend fun getWeather(cityId: Int): Weather =
        apiService.loadCurrentWeather("$PREFIX_CITY_ID$cityId").toEntity()

    override suspend fun getForecast(cityId: Int): Forecast =
        apiService.loadForecast("$PREFIX_CITY_ID$cityId").toEntity()

    private companion object {
        const val PREFIX_CITY_ID = "id:"
    }
}