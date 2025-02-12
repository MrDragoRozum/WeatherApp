package ru.rozum.weatherapp.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import ru.rozum.weatherapp.data.local.db.FavouriteCitiesDao
import ru.rozum.weatherapp.data.mapper.toDbModel
import ru.rozum.weatherapp.data.mapper.toEntities
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.domain.repository.FavouriteRepository
import javax.inject.Inject

class FavouriteRepositoryImpl @Inject constructor(
    private val favouriteCitiesDao: FavouriteCitiesDao
) : FavouriteRepository {
    override val favouriteCities: Flow<List<City>>
        get() = favouriteCitiesDao.getFavouriteCities().map { it.toEntities() }

    override fun observeIsFavourite(cityId: Int): Flow<Boolean> =
        favouriteCitiesDao.observeIsFavourite(cityId)

    override suspend fun addToFavourite(city: City) =
        favouriteCitiesDao.addToFavourite(city.toDbModel())

    override suspend fun removeFromFavorite(cityId: Int) =
        favouriteCitiesDao.removeFromFavourite(cityId)
}