package ru.rozum.weatherapp.domain.repository

import kotlinx.coroutines.flow.Flow
import ru.rozum.weatherapp.domain.entity.City

interface FavouriteRepository {
    val favouriteCities: Flow<List<City>>
    fun observeIsFavourite(cityId: Int): Flow<Boolean>
    suspend fun addToFavourite(city: City)
    suspend fun removeFromFavorite(cityId: Int)
}