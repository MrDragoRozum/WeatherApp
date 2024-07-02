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
import kotlinx.coroutines.launch
import ru.rozum.weatherapp.data.network.api.ApiFactory
import ru.rozum.weatherapp.presentation.theme.WeatherAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val apiService = ApiFactory.apiService

        lifecycleScope.launch {
            apiService.loadCurrentWeather("London").also { println("Current Weather: $it")}
            apiService.loadForecast("London").also { println("Forecast Weather: $it")}
            apiService.searchCity("London").also { println("Cities: $it") }

        }
        setContent {
            WeatherAppTheme {
            }
        }
    }
}
