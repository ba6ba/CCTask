package com.example.sarwan.cctask.modules.restaurant

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.extensions.applyText
import com.example.sarwan.cctask.model.RestaurantResponse
import kotlinx.android.synthetic.main.restaurant_item.view.*

class RestaurantItemsAdapter(private val activity: Activity, private val itemsList : List<RestaurantResponse>) : RecyclerView.Adapter<RestaurantItemsAdapter.RestaurantTopThreeItemsViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantTopThreeItemsViewHolder {
        return RestaurantTopThreeItemsViewHolder(LayoutInflater.from(activity).inflate(R.layout.restaurant_item, parent, false))
    }

    override fun getItemCount(): Int = itemsList.size

    override fun onBindViewHolder(holder: RestaurantTopThreeItemsViewHolder, position: Int) = holder.bind(position)


    inner class RestaurantTopThreeItemsViewHolder(view : View) : RecyclerView.ViewHolder(view) {
        fun bind(position: Int) {
            with(itemView){
                itemsList[position].also {
                    if (position>3) top_3_badge?.visibility = View.GONE
                    name?.applyText(it.name)
                    sub_name?.applyText(it.subcat_name)
                    rating?.applyText(it.rating)
                }
            }
        }
    }
}