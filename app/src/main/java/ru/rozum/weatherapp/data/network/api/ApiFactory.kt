package ru.rozum.weatherapp.data.network.api

import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import retrofit2.create
import ru.rozum.weatherapp.BuildConfig
import java.util.Locale

object ApiFactory {

    private const val KEY_PARAM = "key"
    private const val PARAM_LANG = "lang"
    private const val BASE_URL = "https://api.weatherapi.com/v1/"
    private val json = Json {
        isLenient = true
        ignoreUnknownKeys = true
    }

    private val okHttpClient = OkHttpClient.Builder()
        .addInterceptor { chain ->
            val originalRequest = chain.request()

            val newUrl = originalRequest.url
                .newBuilder()
                .addQueryParameter(KEY_PARAM, BuildConfig.WEATHER_API_KEY)
                .addQueryParameter(PARAM_LANG, Locale.getDefault().language)
                .build()

            val newRequest = originalRequest.newBuilder()
                .url(newUrl)
                .build()
            chain.proceed(newRequest)
        }.build()

    private val retrofit = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(json.asConverterFactory("application/json; charset=UTF8".toMediaType()))
        .build()

    val apiService: ApiService = retrofit.create()
}