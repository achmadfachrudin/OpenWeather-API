package com.fachrudin.base.network

import android.content.Context
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit
import com.ashokvarma.gander.GanderInterceptor
import com.fachrudin.base.BuildConfig


/**
 * @author achmad.fachrudin
 * @date 3-Mar-19
 */
object RetrofitFactory {

    fun makeRetrofitService(context: Context): SampleService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_WEATHER)
            .client(makeOkHttpClient(context))
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(SampleService::class.java)
    }

    private fun makeOkHttpClient(context: Context): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .addInterceptor(GanderInterceptor(context).showNotification(true))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    fun makeRetrofitServiceForTest(): SampleService {
        return Retrofit.Builder()
            .baseUrl(BuildConfig.API_WEATHER)
            .client(makeOkHttpClientForTest())
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .build().create(SampleService::class.java)
    }

    private fun makeOkHttpClientForTest(): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(makeLoggingInterceptor())
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .writeTimeout(90, TimeUnit.SECONDS)
            .build()
    }

    private fun makeLoggingInterceptor(): HttpLoggingInterceptor {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return logging
    }
}