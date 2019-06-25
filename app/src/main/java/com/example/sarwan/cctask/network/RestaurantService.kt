package com.example.sarwan.cctask.network

import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.model.RestaurantResponse
import com.example.sarwan.cctask.model.WeatherResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RestaurantService {

    @GET("search/nearest")
    fun fetchRestaurantList(@Query("apikey") appid: String = Global.TPL_MAPS_KEY,
                         @Query("name") name: String = "Restaurant",
                         @Query("point") point: String,
                         @Query("limit") limit: Int = 30): Call<ArrayList<RestaurantResponse>>
}