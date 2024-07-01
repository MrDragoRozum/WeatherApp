package ru.rozum.weatherapp.domain.repository

import ru.rozum.weatherapp.domain.entity.City

interface SearchRepository {
    suspend fun search(query: String): List<City>
}