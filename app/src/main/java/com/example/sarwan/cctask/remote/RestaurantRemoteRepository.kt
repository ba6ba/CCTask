package com.example.sarwan.cctask.remote

import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.network.*
import com.google.android.gms.maps.model.LatLng

class RestaurantRemoteRepository(private val latLng: LatLng) : RestClient<RestaurantService>(baseUrl = Global.TPL_BASE_URL, resultType = RestaurantService::class.java){

    fun callAsync() = RestaurantDataSource(getRetrofitClient(getUnsafeOkHttpClient())).fetchRestaurantListAysnc(lat = latLng.latitude, lon = latLng.longitude)

}