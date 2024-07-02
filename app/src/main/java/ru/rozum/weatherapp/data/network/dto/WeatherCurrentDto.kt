package ru.rozum.weatherapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeatherCurrentDto(
    @SerialName("current") val current: WeatherDto,
)
