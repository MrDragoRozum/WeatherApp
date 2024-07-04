package ru.rozum.weatherapp.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.lifecycleScope
import com.arkivanov.decompose.defaultComponentContext
import kotlinx.coroutines.launch
import ru.rozum.weatherapp.WeatherApp
import ru.rozum.weatherapp.data.network.api.ApiFactory
import ru.rozum.weatherapp.domain.entity.Weather
import ru.rozum.weatherapp.presentation.root.DefaultRootComponent
import ru.rozum.weatherapp.presentation.root.RootComponent
import ru.rozum.weatherapp.presentation.root.RootContent
import ru.rozum.weatherapp.presentation.theme.WeatherAppTheme
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
