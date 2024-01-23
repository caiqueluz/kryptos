package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.caiqueluz.kryptos.ui.HomeItemFactory
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class HomeViewModel(
    factory: HomeItemFactory
) : ViewModel() {

    private val _items = MutableStateFlow(value = factory.create())
    val items = _items.asStateFlow()
}
