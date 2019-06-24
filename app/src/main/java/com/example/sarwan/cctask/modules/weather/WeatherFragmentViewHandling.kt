package com.example.sarwan.cctask.modules.weather

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.extensions.applyText
import com.example.sarwan.cctask.model.WeatherResponse
import com.example.sarwan.cctask.model.WeatherResult
import kotlinx.android.synthetic.main.fragment_weather.view.*

class WeatherFragmentViewHandling(private val activity: Activity , private val view : View, private val weatherData : WeatherResponse) {
    init {
        if (weatherData.list.isNotEmpty()){
            setTodayData(weatherData.list.first())
            setNextDaysData(weatherData.list - weatherData.list.first())
        }
    }

    private fun setTodayData(today: WeatherResult) {
        with(view){
            today.apply {
                if (weather?.isNotEmpty() == true){
                    current_weather_color?.setBackgroundColor(activity.resources.getColor(weather?.first()?.bgColor?: R.color.colorAccent))
                    status?.applyText(weather?.first()?.main)
                    icon?.setBackgroundDrawable(activity.resources.getDrawable(weather?.first()?.iconRes?:R.drawable.ic_clear))
                }
                city?.applyText(weatherData.city?.name)
                temperature?.applyText(temp?.day)
                _humidity?.applyText(humidity)
                _pressure?.applyText(pressure)
            }
        }
    }

    private fun setNextDaysData(nextDays: List<WeatherResult>) {
        with(view){
            next_days_rv?.apply {
                layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                adapter = WeatherItemsAdapter(activity, nextDays)
            }
        }
    }
}