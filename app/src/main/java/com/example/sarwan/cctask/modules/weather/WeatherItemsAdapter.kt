package com.example.sarwan.cctask.modules.weather

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.extensions.applyText
import com.example.sarwan.cctask.model.WeatherResult
import com.example.sarwan.cctask.modules.restaurant.RestaurantItemsAdapter
import kotlinx.android.synthetic.main.weather_item.view.*

class WeatherItemsAdapter(private val activity: Activity , private val itemsList : List<WeatherResult>) : RecyclerView.Adapter<RestaurantItemsAdapter.WeatherItemsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherItemsViewHolder {
        return WeatherItemsViewHolder(LayoutInflater.from(activity).inflate(R.layout.weather_item, parent, false))
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: WeatherItemsViewHolder, position: Int) = holder.bind(position)


    inner class WeatherItemsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            with(itemView){
                itemsList[position].also {
                    status?.applyText(it.weather?.component1()?.main)
                    temp?.applyText(it.temp?.day)
                    _date?.applyText(it.date)
                }
            }
        }
    }
}