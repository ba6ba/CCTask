package com.example.sarwan.cctask.extensions

import com.example.sarwan.cctask.utils.DateTimeUtility
import java.util.*

fun Long?.convertUnixTime() = DateTimeUtility.sdf.format(Date(this?.times(1000L)?:Calendar.getInstance().timeInMillis))