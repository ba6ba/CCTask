package com.example.sarwan.cctask.model

import com.example.sarwan.cctask.extensions.convertUnixTime
import com.example.sarwan.cctask.modules.weather.WeatherFragmentUtils
import java.io.Serializable

class WeatherResponse : Serializable {
    var city : City ? = null
    var cnt : Int = 0
    var list : ArrayList<WeatherResult> = ArrayList()
}

class City : Serializable {
    var name : String = ""
    var lat : Double = 0.toDouble()
    var lon : Double = 0.toDouble()
    var geoname_id : Long = 9L
}

class WeatherResult : Serializable {
    var dt : Long = 0L
    val date = dt.convertUnixTime()
    var temp : Temp ? = null
    var pressure : Double  = 0.toDouble()
    var humidity : Int  = 0
    var weather : ArrayList<Weather> ? = null

}

class Temp : Serializable {
    var day : Double  = 0.toDouble()
    var min : Double  = 0.toDouble()
    var max : Double  = 0.toDouble()
    var night : Double  = 0.toDouble()
    var eve : Double  = 0.toDouble()
    var morn : Double  = 0.toDouble()
}

class Weather : Serializable {
    var main : String = ""
    var description : String = ""
    val iconRes : Int? = WeatherFragmentUtils.iconRes[main]
    val bgColor : Int? = WeatherFragmentUtils.backgroundColor[main]
}