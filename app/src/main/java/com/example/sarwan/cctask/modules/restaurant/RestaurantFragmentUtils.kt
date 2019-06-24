package com.example.sarwan.cctask.modules.restaurant

import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.extensions.inBetween

object RestaurantFragmentUtils {

    fun getRating(priority : Double): Float  {
        return if (priority.inBetween(1000.0, 10000.0)) 1.0f else if (priority.inBetween(-100000.0, -10000.0)) 2.0f else 3.0f
    }

}