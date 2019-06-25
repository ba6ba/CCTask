package com.example.sarwan.cctask.modules.restaurant

import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.extensions.inBetween
import kotlin.random.Random

object RestaurantFragmentUtils {

    fun getRating(): Float  {
        return (1..5).random().toFloat()
    }
}