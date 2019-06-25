package com.example.sarwan.cctask.model

import com.example.sarwan.cctask.extensions.convertUnixTime
import com.example.sarwan.cctask.modules.restaurant.RestaurantFragmentUtils
import com.example.sarwan.cctask.modules.weather.WeatherFragmentUtils
import java.io.Serializable

class WeatherResponse : Serializable {
    var city : City ? = null
    var cnt : Int = 0
    var list : ArrayList<WeatherResult> = ArrayList()
}

class City : Serializable {
    var name : String = ""
    var id : Long = 0
    var coord : Coordinates ? = null
    var country : String = ""
}

class Coordinates : Serializable {
    var lat : Double = 0.toDouble()
    var lon : Double = 0.toDouble()
}

class WeatherResult : Serializable {
    var dt : Long = 0L
    var date = dt.convertUnixTime()
    var main : Temp ? = null
    var weather : ArrayList<Weather> ? = null
}

class Temp : Serializable {
    var temp : Double  = 0.toDouble()
    var temp_min : Double  = 0.toDouble()
    var temp_max : Double  = 0.toDouble()
    var pressure : Double  = 0.toDouble()
    var sea_level : Double  = 0.toDouble()
    var humidity : Int  = 0
}

class Weather : Serializable {
    var main : String = ""
    var description : String = ""
    var iconRes : Int? = WeatherFragmentUtils.iconRes[main]
    var bgColor : Int? = WeatherFragmentUtils.backgroundColor[main]
}
