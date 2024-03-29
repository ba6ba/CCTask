package com.example.sarwan.cctask.app

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.sarwan.cctask.R
import com.google.android.gms.maps.model.LatLng
import com.google.android.material.snackbar.Snackbar

abstract class BaseActivity : AppCompatActivity() {

    fun getRepository() = (application as CCApp).getRepository()

    fun showMessage(message : String = resources.getString(R.string.something_went_wrong))  =
        Toast.makeText(this,message, Toast.LENGTH_LONG).show()

    fun saveLocationInSharedPreferences(latLng : LatLng) = getRepository().saveLocationInPrefs(latLng)

    fun getLocationFromSharedPreferences(success : (LatLng) -> Unit, failure : (String) -> Unit) = getRepository().getLocationFromPrefs(success, failure)

    fun showSnackBar(mainTextStringId: Int, actionStringId: Int,
                     listener: View.OnClickListener) {
        Snackbar.make(
            findViewById(android.R.id.content),
            getString(mainTextStringId),
            Snackbar.LENGTH_INDEFINITE)
            .setAction(getString(actionStringId), listener).show()
    }

    override fun onBackPressed() {
        finish()
    }
}