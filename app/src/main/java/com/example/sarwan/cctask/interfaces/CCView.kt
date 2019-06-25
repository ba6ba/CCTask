package com.example.sarwan.cctask.interfaces

import android.view.View

interface CCView<T> {
    fun updateData(view : View?, `object` : T?)
    fun clickListener()
}