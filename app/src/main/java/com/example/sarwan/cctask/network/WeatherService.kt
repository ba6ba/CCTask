package com.example.sarwan.cctask.network

import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherService {

    @GET("forecast/")
    fun fetchWeatherList(@Query("appid") appid: String = Global.OPEN_WEATHER_MAP_API_KEY,
                         @Query("lat") lat: String,
                         @Query("lon") lon: String,
                         @Query("units") units: String = "metric"): Call<WeatherResponse>
}