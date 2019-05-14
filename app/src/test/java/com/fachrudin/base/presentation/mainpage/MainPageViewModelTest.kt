package com.fachrudin.base.presentation.mainpage

import com.fachrudin.base.BuildConfig
import com.fachrudin.base.network.RetrofitFactory
import com.fachrudin.base.presentation.main.MainPageViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.MockitoAnnotations

/**
 * Created by achmad.fachrudin on 18-Mar-19
 */
class MainPageViewModelTest {
    @Mock
    lateinit var viewModel: MainPageViewModel

    private val service = RetrofitFactory.makeRetrofitServiceForTest()

    @Before
    fun setUp() {
        Dispatchers.resetMain()
        MockitoAnnotations.initMocks(this)
        viewModel = MainPageViewModel(null)
    }

    @Test
    fun getWeatherFromApi() {
        Dispatchers.setMain(Dispatchers.Unconfined)

        // expected
        val expectedResult = "Bekasi"

        // actual
        val actualRespone = runBlocking {
            service.getWeatherAsync(
                "Bekasi",
                BuildConfig.API_KEY
            ).await()
        }
        val actualResult = actualRespone.body()

        delay()

        Assert.assertEquals(expectedResult, actualResult?.name)
    }

    private fun delay() {
        try {
            Thread.sleep(3000)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }
}