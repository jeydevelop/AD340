package com.ad.ad340.api

import retrofit2.Retrofit

fun createOpenWeatherMapService(): OpenWeatherMapService {
    val retrofit = Retrofit.Builder()
        .baseUrl("http://api.openweathermap.org")
        .build()

    return retrofit.create(OpenWeatherMapService::class.java)
}

interface OpenWeatherMapService {

}