package com.example.sarwan.cctask.modules.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.app.BaseFragment
import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.model.GenericModel
import com.example.sarwan.cctask.model.WeatherResponse
import com.example.sarwan.cctask.utils.navigate
import kotlinx.android.synthetic.main.fragment_weather.*

class WeatherFragment : BaseFragment(), com.example.sarwan.cctask.interfaces.View{

    override fun updateData(view : View) {
        WeatherFragmentViewHandling(baseActivity, view , WeatherResponse())
    }

    override fun clickListener() {
        show_restaurants?.setOnClickListener {
            navigateToRestaurant()
        }
    }

    private fun navigateToRestaurant() {
        navigate(R.id.action_weatherFragment_to_RestaurantFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_weather, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateData(view)
        clickListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        @JvmStatic
        fun newInstance(param : GenericModel) = WeatherFragment().apply {
            arguments = Bundle(1).apply {
                putSerializable(Global.PARAM_KEY, param)
            }
        }
    }

}