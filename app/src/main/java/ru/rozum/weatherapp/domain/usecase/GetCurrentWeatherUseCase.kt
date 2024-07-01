package ru.rozum.weatherapp.domain.usecase

import ru.rozum.weatherapp.domain.repository.FavouriteRepository
import ru.rozum.weatherapp.domain.repository.WeatherRepository
import javax.inject.Inject

class GetCurrentWeatherUseCase @Inject constructor(
    private val repository: WeatherRepository
) {
    suspend operator fun invoke(cityId: Int) = repository.getWeather(cityId)
}