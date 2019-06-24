package com.example.sarwan.cctask.extensions

import android.view.View
import android.widget.TextView

fun TextView.applyText(string: Any?) = if (string.toString().isEmpty()) visibility = View.GONE else text = string?.toString()

