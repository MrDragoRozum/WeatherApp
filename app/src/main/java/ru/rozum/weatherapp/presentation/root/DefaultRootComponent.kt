package ru.rozum.weatherapp.presentation.root

import com.arkivanov.decompose.ComponentContext
import com.arkivanov.decompose.router.stack.ChildStack
import com.arkivanov.decompose.value.Value
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import kotlinx.serialization.Serializable
import ru.rozum.weatherapp.domain.entity.City
import ru.rozum.weatherapp.presentation.details.DefaultDetailsComponent
import ru.rozum.weatherapp.presentation.favourite.DefaultFavouriteComponent
import ru.rozum.weatherapp.presentation.search.DefaultSearchComponent
import ru.rozum.weatherapp.presentation.search.OpenReason

class DefaultRootComponent @AssistedInject constructor(
    private val detailsComponent: DefaultDetailsComponent.Factory,
    private val favouriteComponent: DefaultFavouriteComponent.Factory,
    private val searchComponent: DefaultSearchComponent.Factory,
    @Assisted("componentContext") componentContext: ComponentContext
) : RootComponent,
    ComponentContext by componentContext {

    override val stack: Value<ChildStack<*, RootComponent.Child>>

    private fun child(
        config: Config,
        componentContext: ComponentContext
    ): RootComponent.Child = when (config) {
        is Config.Details -> {
            val component = detailsComponent.create(
                city = config.city,
                componentContext = componentContext,
                onClickBack = {

                }
            )
            RootComponent.Child.Details(component)
        }

        Config.Favourite -> {
            val component = favouriteComponent.create(
                componentContext = componentContext,
                onCityItemClicked = {

                },
                onAddFavouriteClicked = {

                },
                onSearchClicked = {

                })
            RootComponent.Child.Favourite(component)
        }

        is Config.Search -> {
            val component = searchComponent.create(
                openReason = config.openReason,
                onBackClick = {

                },
                onCitySavedToFavourite = {

                },
                onForecastForCityRequested = {

                },
                componentContext = componentContext
            )
            RootComponent.Child.Search(component)
        }
    }

    @Serializable
    private sealed interface Config {

        @Serializable
        data object Favourite : Config

        @Serializable
        data class Search(val openReason: OpenReason) : Config

        @Serializable
        data class Details(val city: City) : Config
    }

    @AssistedFactory
    interface Factory {
        fun create(
            @Assisted("componentContext") componentContext: ComponentContext
        ): DefaultRootComponent
    }
}