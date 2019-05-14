package com.fachrudin.base.presentation.splashscreen

import android.os.Bundle
import com.fachrudin.base.R
import com.fachrudin.base.core.BaseActivity
import com.fachrudin.base.core.ViewDataBindingOwner
import com.fachrudin.base.databinding.ActivitySplashScreenBinding
import com.fachrudin.base.presentation.main.MainPageActivity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

/**
 * @author achmad.fachrudin
 * @date 10-Mar-2019
 */
class SplashScreenActivity : BaseActivity(),
    SplashScreenView,
    ViewDataBindingOwner<ActivitySplashScreenBinding> {

    override fun getViewLayoutResId(): Int {
        return R.layout.activity_splash_screen
    }

    private lateinit var viewModel: SplashScreenViewModel
    override lateinit var binding: ActivitySplashScreenBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.vm = SplashScreenViewModel()
        viewModel = binding.vm!!

        GlobalScope.launch(Dispatchers.Main) {
            delay(2100)
            MainPageActivity.startThisActivity(this@SplashScreenActivity)
            finish()
        }
    }
}