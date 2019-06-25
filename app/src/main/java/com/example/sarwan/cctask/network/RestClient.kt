package com.example.sarwan.cctask.network

import android.app.Activity
import android.provider.Settings
import android.util.Log
import com.example.sarwan.cctask.BuildConfig
import com.google.gson.GsonBuilder
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.net.ssl.SSLContext
import javax.net.ssl.TrustManager
import javax.net.ssl.X509TrustManager

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

    fun getRetrofitClient(httpClient : OkHttpClient): ResultType {
        val retrofit = Retrofit.Builder().
                baseUrl(baseUrl).
                addConverterFactory(GsonConverterFactory.create(GsonBuilder().setLenient().create())).
                client(httpClient).
                build()

        return retrofit.create<ResultType>(resultType)
    }


    fun getSafeOkHttpClient(interceptor: Interceptor = httpClientInterceptor()) : OkHttpClient =
        OkHttpClient.Builder().addInterceptor(interceptor).
            addInterceptor(interceptor).
            connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
            .addInterceptor(logging as HttpLoggingInterceptor)
            .build()

    fun httpClientInterceptor() : Interceptor = Interceptor { chain ->
        val request = chain.request()
        val newRequest: Request
        newRequest = request.newBuilder()
            .addHeader("Accept","application/json")
            .build()
        chain.proceed(newRequest)
    }

    fun getUnsafeOkHttpClient(interceptor: Interceptor = httpClientInterceptor()): OkHttpClient {
        try {
            // Create a trust manager that does not validate certificate chains
            val trustAllCerts = arrayOf<TrustManager>(object : X509TrustManager {
                override fun checkClientTrusted(
                    chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {

                }

                override fun checkServerTrusted(
                    chain: Array<out java.security.cert.X509Certificate>?, authType: String?) {
                }

                override fun getAcceptedIssuers(): Array<java.security.cert.X509Certificate> {
                    return emptyArray()
                }
            })

            // Install the all-trusting trust manager
            val sslContext = SSLContext.getInstance("SSL")
            sslContext.init(null, trustAllCerts, java.security.SecureRandom())

            // Create an ssl socket factory with our all-trusting manager
            val sslSocketFactory = sslContext.socketFactory

            return OkHttpClient.Builder().apply {
                sslSocketFactory(sslSocketFactory, trustAllCerts[0] as X509TrustManager)
                hostnameVerifier { hostname, session -> true }
                addInterceptor(interceptor).connectTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .writeTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .readTimeout(TIMEOUT.toLong(), TimeUnit.SECONDS)
                    .addInterceptor(logging as HttpLoggingInterceptor)
            }.build()
        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }
}
