package com.example.sarwan.cctask.app

import android.os.Bundle
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.modules.location.LocationBaseActivity
import com.google.android.gms.maps.model.LatLng

class MainActivity : LocationBaseActivity() {

    override fun onSuccess(`object`: Any) {
        saveLocationInSharedPreferences(`object` as LatLng)
    }

    override fun onFailure(error: String) {
        showMessage(error)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
