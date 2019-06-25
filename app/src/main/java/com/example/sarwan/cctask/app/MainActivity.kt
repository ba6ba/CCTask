package com.example.sarwan.cctask.app

import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.modules.SplashFragment
import com.example.sarwan.cctask.modules.location.LocationBaseActivity
import com.google.android.gms.maps.model.LatLng

class MainActivity : LocationBaseActivity() {

    override fun onSuccess(`object`: Any) {
        saveLocationInSharedPreferences(`object` as LatLng)
    }

    override fun onFailure(error: String) {
        showMessage(error)
    }

    override fun havePermission(have: Boolean) {
        sendActionToFragment(have)
    }

    private fun sendActionToFragment(have: Boolean) {
        if (supportFragmentManager.fragments.isNotEmpty()){
            ((supportFragmentManager.fragments.find { it is NavHostFragment }
                    as? NavHostFragment)?.childFragmentManager?.fragments?.get(0) as? SplashFragment)?.navigateToNext(have)
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
}
