package com.example.sarwan.cctask.network

/**
 * Implementation of [RestaurantService] interface
 */
class RestaurantDataSource(private val restaurantService: RestaurantService) {

    fun fetchRestaurantListAysnc(lat : Double , lon : Double) =
        restaurantService.fetchRestaurantList(point = "$lat;$lon")
}