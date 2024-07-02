package ru.rozum.weatherapp.data.mapper

import ru.rozum.weatherapp.data.local.model.CityDbModel
import ru.rozum.weatherapp.domain.entity.City

fun CityDbModel.toEntity(): City = City(id, name, country)

fun City.toDbModel(): CityDbModel = CityDbModel(id, name, country)

fun List<CityDbModel>.toEntities(): List<City> = map { it.toEntity() }