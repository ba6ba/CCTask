package com.example.sarwan.cctask.app

import android.os.Bundle
import androidx.fragment.app.Fragment

open class BaseFragment : Fragment() {

    protected lateinit var baseActivity : BaseActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        baseActivity = activity as BaseActivity
    }
}