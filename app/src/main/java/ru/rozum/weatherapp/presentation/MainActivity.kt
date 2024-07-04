package ru.rozum.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.launch
import ru.rozum.weatherapp.WeatherApp
import ru.rozum.weatherapp.domain.usecase.ChangeFavouriteCitiesUseCase
import ru.rozum.weatherapp.domain.usecase.SearchCityUseCase
import ru.rozum.weatherapp.presentation.root.DefaultRootComponent
import ru.rozum.weatherapp.presentation.root.RootContent
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var rootComponentFactory: DefaultRootComponent.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        (application as WeatherApp).applicationComponent.inject(this)
        super.onCreate(savedInstanceState)

        val root = rootComponentFactory.create(defaultComponentContext())


        setContent {
            RootContent(component = root)
        }
    }
}
