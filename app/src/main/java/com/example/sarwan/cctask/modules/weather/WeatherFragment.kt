package com.example.sarwan.cctask.modules.weather

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.app.BaseFragment
import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.interfaces.CCView
import com.example.sarwan.cctask.model.GenericModel
import com.example.sarwan.cctask.model.WeatherResponse
import com.example.sarwan.cctask.remote.WeatherRemoteRepository
import com.example.sarwan.cctask.utils.navigate
import kotlinx.android.synthetic.main.fragment_weather.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class WeatherFragment : BaseFragment(), CCView<WeatherResponse>{

    override fun updateData(view: View?, `object`: WeatherResponse?) {
        `object`?.let {
            WeatherFragmentViewHandling(baseActivity, view , `object`)
        }
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
        clickListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getWeatherForecast()
    }

    private fun getWeatherForecast() {
        baseActivity.getRepository().getLocationFromPrefs(success = {
            WeatherRemoteRepository(it).callAsync().enqueue(apiCallBack)
        },
            failure = {
                baseActivity.showMessage(it)
            })
    }

    private val apiCallBack = object : Callback<WeatherResponse>{
        override fun onFailure(call: Call<WeatherResponse>, t: Throwable) {
            baseActivity.showMessage(t.localizedMessage?:"")
        }

        override fun onResponse(call: Call<WeatherResponse>, response: Response<WeatherResponse>) {
            if (response.isSuccessful){
                updateData(view, response.body())
            }
        }
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