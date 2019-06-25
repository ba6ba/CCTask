package com.example.sarwan.cctask.modules

import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.app.BaseFragment
import com.example.sarwan.cctask.utils.navigate
import kotlinx.android.synthetic.main.splash_fragment.*

class SplashFragment : BaseFragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.splash_fragment, container, false)
    }

    private fun doAnimation() {
        splash_logo?.animation = AnimationUtils.loadAnimation(baseActivity, R.anim.scale_up).apply { duration = 2000L }
    }

    fun navigateToNext(navigate : Boolean) {
        if (navigate){
            doAnimation()
            navigate(R.id.action_splashFragment_to_WeatherFragment)
        }else {
            baseActivity.showMessage(baseActivity.resources.getString(R.string.permission_denied_explanation))
        }
    }
}