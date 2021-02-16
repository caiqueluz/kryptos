package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.network.NetworkResponse.Companion.mapContent
import com.caiqueluz.kryptos.network.asNetworkResponse
import com.caiqueluz.kryptos.network.networkResponseLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: CurrenciesRepository,
    private val converter: CurrenciesConverter
) : ViewModel() {

    private val _currencies = MutableLiveData<Unit>()
    val currencies: LiveData<NetworkResponse<CurrenciesVO>> =
        _currencies.switchMap {
            return@switchMap networkResponseLiveData {
                repository.fetchCurrencies()
                    .asNetworkResponse()
                    .mapContent { dto -> converter.convert(dto) }
            }
        }

    fun fetchCurrencies() {
        _currencies.postValue(Unit)
    }
}
