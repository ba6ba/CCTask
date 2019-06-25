package com.example.sarwan.cctask.modules.restaurant

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.sarwan.cctask.R
import com.example.sarwan.cctask.app.BaseFragment
import com.example.sarwan.cctask.extras.Global
import com.example.sarwan.cctask.interfaces.CCView
import com.example.sarwan.cctask.model.GenericModel
import com.example.sarwan.cctask.model.RestaurantResponse
import com.example.sarwan.cctask.remote.RestaurantRemoteRepository
import com.example.sarwan.cctask.utils.navigate
import kotlinx.android.synthetic.main.fragment_restaurant.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RestaurantFragment : BaseFragment(), CCView<ArrayList<RestaurantResponse>> {

    override fun updateData(view: View?, `object`: ArrayList<RestaurantResponse>?) {
        `object`?.let {
            RestaurantFragmentViewHandling(baseActivity, view , `object`)
        }
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
        clickListener()
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        getRestaurantList()
    }

    private fun getRestaurantList() {
        baseActivity.getRepository().getLocationFromPrefs(success = {
            RestaurantRemoteRepository(it).callAsync().enqueue(apiCallBack)
        },
            failure = {
                baseActivity.showMessage(it)
            })
    }

    private val apiCallBack = object : Callback<ArrayList<RestaurantResponse>> {
        override fun onFailure(call: Call<ArrayList<RestaurantResponse>>, t: Throwable) {
            baseActivity.showMessage(t.localizedMessage?:"")
        }

        override fun onResponse(call: Call<ArrayList<RestaurantResponse>>, response: Response<ArrayList<RestaurantResponse>>) {
            if (response.isSuccessful){
                updateData(view, `object` = response.body())
            }
        }
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