package com.example.sarwan.cctask.modules.restaurant

import android.app.Activity
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.cctask.model.RestaurantResponse
import kotlinx.android.synthetic.main.fragment_restaurant.view.*

class RestaurantFragmentViewHandling(private val activity: Activity, private val view : View?, private val restaurantResponse: ArrayList<RestaurantResponse>) {
    init {
        if (restaurantResponse.isNotEmpty()){
            setRestaurants(restaurantResponse)
        }
    }

    private fun setRestaurants(restaurant: List<RestaurantResponse>) {
        view?.apply {
            restaurants_rv?.apply {
                layoutManager = LinearLayoutManager(activity, RecyclerView.HORIZONTAL, false)
                adapter = RestaurantItemsAdapter(activity, makeList(restaurant))
            }
        }
    }

    private fun makeList(restaurant: List<RestaurantResponse>) = arrayListOf<RestaurantResponse>().apply {
        addAll(restaurant.filter { it.rating == 5.0f }.take(3))
        addAll(restaurant.filter { it.rating != 5.0f }.take(2))
        sortedBy { it.rating == 5.0f }
    }
}