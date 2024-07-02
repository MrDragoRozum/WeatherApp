package ru.rozum.weatherapp.di

import android.content.Context
import dagger.Binds
import dagger.Module
import dagger.Provides
import ru.rozum.weatherapp.data.local.db.FavouriteCitiesDao
import ru.rozum.weatherapp.data.local.db.FavouriteDatabase
import ru.rozum.weatherapp.data.network.api.ApiFactory
import ru.rozum.weatherapp.data.network.api.ApiService
import ru.rozum.weatherapp.data.repository.FavouriteRepositoryImpl
import ru.rozum.weatherapp.data.repository.SearchRepositoryImpl
import ru.rozum.weatherapp.data.repository.WeatherRepositoryImpl
import ru.rozum.weatherapp.domain.repository.FavouriteRepository
import ru.rozum.weatherapp.domain.repository.SearchRepository
import ru.rozum.weatherapp.domain.repository.WeatherRepository

@Module
interface DataModule {

    @[ApplicationScope Binds]
    fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository

    @[ApplicationScope Binds]

    fun bindWeatherRepository(impl: WeatherRepositoryImpl): WeatherRepository

    @[ApplicationScope Binds]

    fun bindSearchRepository(impl: SearchRepositoryImpl): SearchRepository

    companion object {
        @[ApplicationScope Provides]
        fun provideApiService(): ApiService = ApiFactory.apiService

        @[ApplicationScope Provides]
        fun provideFavouriteDatabase(context: Context): FavouriteDatabase =
            FavouriteDatabase.getInstance(context)

        @[ApplicationScope Provides]
        fun provideFavouriteCitiesDao(database: FavouriteDatabase): FavouriteCitiesDao =
            database.favouriteCitiesDao()
    }
}