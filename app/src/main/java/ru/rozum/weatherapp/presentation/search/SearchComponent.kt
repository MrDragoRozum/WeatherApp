package ru.rozum.weatherapp.presentation.search

import kotlinx.coroutines.flow.StateFlow
import ru.rozum.weatherapp.domain.entity.City

interface SearchComponent {
    val model: StateFlow<SearchStore.State>

    fun changeSearchQuery(query: String)
    fun onClickBack()
    fun onClickSearch()
    fun onClickCity(city: City)
}