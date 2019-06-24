package com.example.sarwan.cctask.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtility {
    fun ifToday(dt: String?): Boolean {
        sdf.parse(dt?:"1980-01-01")?.apply {
            return (date == Calendar.getInstance().time.date) && (month == Calendar.getInstance().time.month)
        }
        return false
    }

    val sdf = SimpleDateFormat("yyyy-MM-dd")
}