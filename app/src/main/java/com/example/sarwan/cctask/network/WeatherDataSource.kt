package com.example.sarwan.cctask.network

/**
 * Implementation of [WeatherService] interface
 */
class WeatherDataSource(private val weatherService: WeatherService) {

    fun fetchWeatherListAysnc(lat : Double , lon : Double) =
        weatherService.fetchWeatherList(lat = lat.toString(), lon = lon.toString())
}