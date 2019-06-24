package com.example.sarwan.cctask.repostiory

import android.content.Context
import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.extras.Global.PREFS_NAME
import com.google.android.gms.maps.model.LatLng
import com.google.gson.Gson

class CCTaskRepository(private val context : Context) {

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun saveLocationInPrefs(latLng : LatLng)  = preferences.edit().putString(Global.LATLNG, Gson().toJson(latLng)).apply()

    fun getLocationFromPrefs(success : (LatLng) -> Unit, failure : (String) -> Unit) {
        if (preferences.contains(Global.LATLNG)){
            Gson().fromJson(preferences.getString(Global.LATLNG, null), LatLng::class.java)?.apply {
                success(this)
            }?:failure("No LatLng found in shared preferences")
        }
    }
}