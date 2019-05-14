package com.fachrudin.base.presentation.main

import android.content.Context
import androidx.databinding.ObservableField
import androidx.lifecycle.MutableLiveData
import com.fachrudin.base.BuildConfig
import com.fachrudin.base.core.BaseViewModel
import com.fachrudin.base.core.NetworkState
import com.fachrudin.base.entities.OpenWeather
import com.fachrudin.base.network.RetrofitFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

/**
 * @author achmad.fachrudin
 * @date 14-May-19
 */
class MainPageViewModel(context: Context?) : BaseViewModel() {

    private val service =
        if (context == null) RetrofitFactory.makeRetrofitServiceForTest()
        else RetrofitFactory.makeRetrofitService(context)

    private var cityName = ObservableField<String>("Jakarta")

    var bTextCity = ObservableField<String>()
    var bTextWeather = ObservableField<String>()
    var bTextTemp = ObservableField<String>()

    val showLoadingView = ObservableField<Boolean>(true)

    var weather: MutableLiveData<OpenWeather> = MutableLiveData()

    fun getWeatherFromApi() {
        networkState.value = NetworkState.LOADING
        GlobalScope.launch(Dispatchers.Main)
        {
            val request = service.getWeatherAsync(cityName.get()!!, BuildConfig.API_KEY)
            try {
                val response = request.await()
                // Do something with the response.body()
                weather.value = response.body()!!
                networkState.value = NetworkState.LOADED
            } catch (e: Exception) {
                networkState.value = NetworkState.error(e)
            }
        }
    }
}