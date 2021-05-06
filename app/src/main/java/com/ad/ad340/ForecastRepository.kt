package com.ad.ad340

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlin.random.Random

class ForecastRepository {

    private val _currentForecast = MutableLiveData<DailyForecast>()
    val currentForecast: LiveData<DailyForecast> = _currentForecast

    private val _weeklyForecast = MutableLiveData<List<DailyForecast>>()
    val weeklyForecast : LiveData<List<DailyForecast>> = _weeklyForecast


    fun loadWeeklyForecast(zipcode: String) {
        val randomValues = List(7) { Random. nextFloat().rem(100) * 100 }
        val forecastItems = randomValues.map {temp ->
            DailyForecast(temp, getTempDescription(temp))
        }
        _weeklyForecast.setValue(forecastItems)
    }

    fun loadCurrentForecast(zipcode: String) {
        val randomTemp = Random.nextFloat().rem(100) * 100
        val forecast = DailyForecast(randomTemp, getTempDescription(randomTemp))
        _currentForecast.value = forecast
    }

    private fun getTempDescription(temp: Float): String {
        return when (temp) {
            in Float.MIN_VALUE.rangeTo(0f) -> "Anything below 0 doesn't make sense"
            in 0f.rangeTo(32f) -> "Way too cold"
            in 32f.rangeTo(55f) -> "Colder than I would prefer"
            in 55f.rangeTo(65f) -> "Getting Better"
            in 65f.rangeTo(80f) -> "That's the sweet spot!"
            in 80f.rangeTo(90f) -> "Getting a little warm"
            in 90f.rangeTo(100f) -> "Where's the A/C?"
            in 100f.rangeTo(Float.MAX_VALUE) -> "What is this, Arizona?"
            else -> "Does not compute"
        }
    }
}