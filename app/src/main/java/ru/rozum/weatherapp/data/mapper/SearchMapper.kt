package ru.rozum.weatherapp.data.mapper

import ru.rozum.weatherapp.data.network.dto.CityDto
import ru.rozum.weatherapp.domain.entity.City

fun CityDto.toEntity(): City = City(id, name, country)

fun List<CityDto>.toEntities(): List<City> = map { it.toEntity() }