package ru.rozum.weatherapp.presentation.search

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.mvikotlin.core.instancekeeper.getStore
import com.arkivanov.mvikotlin.extensions.coroutines.labels
import com.arkivanov.mvikotlin.extensions.coroutines.stateFlow
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.presentation.extesions.componentScope
import javax.inject.Inject

class DefaultSearchComponent @Inject constructor(
    private val storeFactory: SearchStoreFactory,
    private val openReason: OpenReason,
    private val onBackClick: () -> Unit,
    private val onCitySavedToFavourite: () -> Unit,
    private val onForecastForCityRequested: (City) -> Unit,
    componentContext: ComponentContext
) : SearchComponent, ComponentContext by componentContext {

    private val store = instanceKeeper.getStore { storeFactory.create(openReason) }
    private val scope = componentScope()

    @OptIn(ExperimentalCoroutinesApi::class)
    override val model: StateFlow<SearchStore.State> = store.stateFlow

    init {
        scope.launch {
            store.labels.collect {
                when(it) {
                    SearchStore.Label.ClickBack -> onBackClick()
                    is SearchStore.Label.OpenForecast -> onForecastForCityRequested(it.city)
                    SearchStore.Label.SaveToFavourite -> onCitySavedToFavourite()
                }
            }
        }
    }

    override fun changeSearchQuery(query: String) =
        store.accept(SearchStore.Intent.ChangeSearchQuery(query))

    override fun onClickBack() = store.accept(SearchStore.Intent.ClickBack)

    override fun onClickSearch() = store.accept(SearchStore.Intent.ClickSearch)

    override fun onClickCity(city: City) = store.accept(SearchStore.Intent.ClickCity(city))
}