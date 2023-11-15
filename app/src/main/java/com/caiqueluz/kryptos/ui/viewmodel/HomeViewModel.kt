package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map
import com.caiqueluz.kryptos.ui.HomeItemFactory

class HomeViewModel(
    private val factory: HomeItemFactory
) : ViewModel() {

    private val _items = MutableLiveData<Unit>()
    val items = _items.map {
        return@map factory.create()
    }

    fun onScreenStarted() {
        _items.postValue(Unit)
    }
}
