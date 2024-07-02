package ru.rozum.weatherapp.data.repository

import ru.rozum.weatherapp.data.mapper.toEntities
import ru.rozum.weatherapp.data.network.api.ApiService
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.domain.repository.SearchRepository
import javax.inject.Inject

class SearchRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : SearchRepository {
    override suspend fun search(query: String): List<City> =
        apiService.searchCity(query).toEntities()
}