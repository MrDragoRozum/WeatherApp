package ru.rozum.weatherapp.domain.usecase

import ru.rozum.weatherapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class ObserverFavouriteStateUseCase @Inject constructor(
    private val repository: FavouriteRepository
) {
    operator fun invoke(cityId: Int) = repository.observeIsFavourite(cityId)
}