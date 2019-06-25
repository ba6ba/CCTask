package com.example.sarwan.cctask.remote

import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.network.RestClient
import com.example.sarwan.cctask.network.WeatherDataSource
import com.example.sarwan.cctask.network.WeatherService
import com.google.android.gms.maps.model.LatLng

class WeatherRemoteRepository(private val latLng: LatLng) : RestClient<WeatherService>(baseUrl = Global.WEATHER_BASE_URL, resultType = WeatherService::class.java){

    fun callAsync() = WeatherDataSource(getRetrofitClient(getSafeOkHttpClient())).fetchWeatherListAysnc(lat = latLng.latitude, lon = latLng.longitude)

}