package com.fachrudin.base.core

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.databinding.Observable
import androidx.databinding.PropertyChangeRegistry
import com.fachrudin.base.BaseApplication

/**
 * @author achmad.fachrudin
 * @date 3-Mar-19
 */
abstract class BaseViewModel : AndroidViewModel(BaseApplication()) {
    var initialState: MutableLiveData<NetworkState> = MutableLiveData()
    var networkState: MutableLiveData<NetworkState> = MutableLiveData()

    fun getInitialState(): LiveData<NetworkState> {
        return initialState
    }

    fun getNetworkState(): LiveData<NetworkState> {
        return networkState
    }
}