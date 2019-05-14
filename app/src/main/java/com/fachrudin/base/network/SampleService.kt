package com.fachrudin.base.network

import com.fachrudin.base.entities.OpenWeather
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author achmad.fachrudin
 * @date 3-Mar-19
 */
interface SampleService {
    @GET("weather")
    fun getWeatherAsync(@Query("q") cityName: String,
                        @Query("appid") appId: String): Deferred<Response<OpenWeather>>
}