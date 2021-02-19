package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.caiqueluz.kryptos.data.CurrenciesDTO
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.NetworkResponseLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: CurrenciesRepository,
    private val converter: CurrenciesConverter
) : ViewModel() {

    val currencies = NetworkResponseLiveData<Unit, CurrenciesDTO, CurrenciesVO>(
        request = { repository.fetchCurrencies() },
        transformation = converter::convert
    )
}
