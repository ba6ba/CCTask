package com.example.sarwan.cctask.model

import com.example.sarwan.cctask.modules.restaurant.RestaurantFragmentUtils

class RestaurantResponse {
    var compound_address_parents : String = ""
    var subcat_name : String = ""
    var lng : Double = 0.toDouble()
    var lon : Double = 0.toDouble()
    var fkey : Long = 0L
    var id : Long = 0L
    var name : String = ""
    var priority : Double = 0.toDouble()
    var rating : Float = RestaurantFragmentUtils.getRating(priority)
}