package ru.rozum.weatherapp

import android.app.Application
import ru.rozum.weatherapp.di.ApplicationComponent
import ru.rozum.weatherapp.di.DaggerApplicationComponent

class WeatherApp: Application() {
    lateinit var applicationComponent: ApplicationComponent

    override fun onCreate() {
        super.onCreate()
        applicationComponent = DaggerApplicationComponent.factory().create(this)
    }
}
