package com.example.sarwan.cctask.modules.weather

import android.app.Activity
import android.view.View
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.extensions.applyText
import com.example.sarwan.cctask.extensions.convertUnixTime
import com.example.sarwan.cctask.model.WeatherResponse
import com.example.sarwan.cctask.model.WeatherResult
import com.example.sarwan.cctask.utils.isAfterDay
import kotlinx.android.synthetic.main.fragment_weather.view.*

class WeatherFragmentViewHandling(private val activity: Activity , private val view : View?, private val weatherData : WeatherResponse) {
    init {
        if (weatherData.list.isNotEmpty()){
            weatherData.list.forEach {
                it.date = it.dt.convertUnixTime()
                it.weather?.forEach { weather-> weather.iconRes = WeatherFragmentUtils.iconRes[weather.main]
                    weather.bgColor =  WeatherFragmentUtils.backgroundColor[weather.main]
                }
            }
            setTodayData(weatherData.list.first())
            setNextDaysData(weatherData.list - weatherData.list.first())
        }
    }

    private fun setTodayData(today: WeatherResult) {
        view?.apply {
            today.apply {
                if (weather?.isNotEmpty() == true){
                    current_weather_color?.setBackgroundColor(activity.resources.getColor(weather?.first()?.bgColor?: R.color.colorPrimary))
                    status?.applyText(weather?.first()?.main)
                    icon?.apply {
                        setBackgroundDrawable(activity.resources.getDrawable(weather?.first()?.iconRes?:R.drawable.ic_clear))
                        animation = AnimationUtils.loadAnimation(activity, R.anim.shake_animation)
                    }
                }
                city?.applyText(weatherData.city?.name)
                temperature?.applyText("${main?.temp} °C")
                _humidity?.applyText("${_humidity?.text} ${main?.humidity}")
                _pressure?.applyText("${_pressure?.text} ${main?.pressure}")
            }
        }
    }

    private fun setNextDaysData(nextDays: List<WeatherResult>) {
        view?.apply {
            next_days_rv?.apply {
                layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                adapter = WeatherItemsAdapter(activity, makeList(nextDays))
            }
        }
    }

    private fun makeList(nextDays: List<WeatherResult>) = arrayListOf<WeatherResult>().apply {
        nextDays.forEachIndexed { index, weatherResult ->
            if ((index!=nextDays.size - 1) && (nextDays[index+1].date.isAfterDay(weatherResult.date))) {
                add(weatherResult)
            }
        }
    }
}