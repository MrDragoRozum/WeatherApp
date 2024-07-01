package ru.rozum.weatherapp.domain.usecase

import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class ChangeFavouriteCitiesUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    suspend fun addToFavourite(city: City) = repository.addToFavourite(city)
    suspend fun removeFromFavourite(cityId: Int) = repository.removeFromFavorite(cityId)
}