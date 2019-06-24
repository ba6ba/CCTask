package com.example.sarwan.cctask.network

import android.app.Activity
import android.provider.Settings
import android.util.Log
import com.example.sarwan.cctask.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

abstract class RestClient<ResultType>(private val baseUrl : String, private val resultType : Class<ResultType>) {

    private val TIMEOUT = 25
    private var logging: HttpLoggingInterceptor? = null
    private val TAG: String = "RESTCLIENT"

    init {
        setupClient()
    }

    private fun setupClient() {
        setupLogging()
    }

    private fun setupLogging() {
        logging = HttpLoggingInterceptor()
        logging?.level = if ( BuildConfig.DEBUG ) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
    }

    fun getRetrofitClient(): ResultType {
        val httpClient = OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val request = chain.request()
                    val newRequest: Request
                    newRequest = request.newBuilder()
                            .build()
                    chain.proceed(newRequest)
                }
                .connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS).writeTimeout(
                TIMEOUT.toLong(), TimeUnit.SECONDS).readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                .addInterceptor(logging as HttpLoggingInterceptor)
                .build()

        val retrofit = Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).
                client(httpClient).
                build()

        return retrofit.create<ResultType>(resultType)
    }
}
