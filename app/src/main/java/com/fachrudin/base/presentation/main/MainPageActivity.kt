package com.fachrudin.base.presentation.main

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.fachrudin.base.R
import com.fachrudin.base.core.BaseActivity
import com.fachrudin.base.core.NetworkState
import com.fachrudin.base.core.ViewDataBindingOwner
import com.fachrudin.base.core.widget.LoadingView
import com.fachrudin.base.databinding.ActivityMainPageBinding
import com.fachrudin.base.entities.OpenWeather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author achmad.fachrudin
 * @date 14-May-19
 */
class MainPageActivity : BaseActivity(),
    MainPageView,
    ViewDataBindingOwner<ActivityMainPageBinding> {

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_main_page
    }

    companion object {
        fun startThisActivity(context: Context) {
            val intent = Intent(context, MainPageActivity::class.java)
            context.startActivity(intent)
        }
    }

    private lateinit var viewModel: MainPageViewModel
    override lateinit var binding: ActivityMainPageBinding

    override var retryListener: LoadingView.OnRetryListener
        get() = object : LoadingView.OnRetryListener {
            override fun onRetry() {
                viewModel.getWeatherFromApi()
            }
        }
        set(value) {}

    private var doubleBackPressed = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = MainPageViewModel(this)
        viewModel = binding.vm!!

        initUI()

        viewModel.getWeatherFromApi()
        observeWeather()
        observeNetworkState()
    }

    private fun initUI() {
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(false)
            it.setHomeButtonEnabled(false)
        }

        title = getString(R.string.home_title)
    }

    private fun observeNetworkState() {
        observeData(viewModel.getNetworkState()) { networkState ->
            networkState?.let {
                when (it) {
                    NetworkState.LOADING -> {
                        viewModel.showLoadingView.set(true)
                    }
                    NetworkState.LOADED -> {
                        viewModel.showLoadingView.set(false)
                    }
                    else -> it.exception?.let { e ->
                        binding.loadingView.showError(e, getString(R.string.error_title), getString(R.string.error_msg))
                    }
                }
            }
        }
    }

    private fun observeWeather() {
        observeData(viewModel.weather) { result ->
            result?.let {
                updateWeather(it)
            }
        }
    }

    private fun updateWeather(weather: OpenWeather) {
        viewModel.bTextCity.set(weather.name)
        viewModel.bTextWeather.set(weather.weather[0].main)
        viewModel.bTextTemp.set(weather.main.temp)
    }

    override fun onBackPressed() {
        if (doubleBackPressed) {
            super.onBackPressed()
            return
        }
        this.doubleBackPressed = true
        Toast.makeText(this, getString(R.string.app_msg_close), Toast.LENGTH_SHORT).show()

        GlobalScope.launch(Dispatchers.Main) {
            delay(2000)
            doubleBackPressed = false
        }
    }
}