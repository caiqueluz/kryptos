package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.caiqueluz.kryptos.data.CurrenciesRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: CurrenciesRepository,
    private val converter: CurrenciesConverter
) : ViewModel() {

    //
}
