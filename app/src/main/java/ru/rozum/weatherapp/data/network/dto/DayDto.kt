package ru.rozum.weatherapp.data.network.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DayDto(
    @SerialName("date_epoch") val date: Long,
    @SerialName("day") val dayWeatherDto: DayWeatherDto,

)
