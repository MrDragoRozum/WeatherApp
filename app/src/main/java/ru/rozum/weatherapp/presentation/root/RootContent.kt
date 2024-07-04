package ru.rozum.weatherapp.presentation.root

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.arkivanov.decompose.extensions.compose.stack.Children
import ru.rozum.weatherapp.presentation.details.DetailsContent
import ru.rozum.weatherapp.presentation.favourite.FavouriteContent
import ru.rozum.weatherapp.presentation.search.SearchContent
import ru.rozum.weatherapp.presentation.theme.WeatherAppTheme

@Composable
fun RootContent(modifier: Modifier = Modifier, component: RootComponent) {
    WeatherAppTheme {
        Children(stack = component.stack) {
            when(val instance = it.instance) {
                is RootComponent.Child.Details -> {
                    DetailsContent(component = instance.component)
                }
                is RootComponent.Child.Favourite -> {
                    FavouriteContent(component = instance.component)
                }
                is RootComponent.Child.Search -> {
                    SearchContent(component = instance.component)
                }
            }
        }
    }
}