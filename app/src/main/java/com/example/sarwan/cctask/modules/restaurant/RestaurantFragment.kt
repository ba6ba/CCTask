package com.example.sarwan.cctask.modules.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.app.BaseFragment
import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.model.GenericModel
import com.example.sarwan.cctask.utils.navigate
import kotlinx.android.synthetic.main.fragment_restaurant.*

class RestaurantFragment : BaseFragment(), com.example.sarwan.cctask.interfaces.View{

    override fun updateData(view : View) {
        RestaurantFragmentViewHandling(baseActivity, view , ArrayList())
    }

    override fun clickListener() {
        back?.setOnClickListener {
            navigateToWeather()
        }
    }

    private fun navigateToWeather() {
        navigate(R.id.action_restaurantFragment_to_WeatherFragment)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_restaurant, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        updateData(view)
        clickListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getRestaurantList()
    }

    private fun getRestaurantList() {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


    companion object {
        @JvmStatic
        fun newInstance(param : GenericModel) = RestaurantFragment().apply {
            arguments = Bundle(1).apply {
                putSerializable(Global.PARAM_KEY, param)
            }
        }
    }

}