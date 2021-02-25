package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.mapResponse
import com.caiqueluz.kryptos.ui.combine
import com.caiqueluz.kryptos.ui.networkResponseLiveData
import com.caiqueluz.kryptos.utils.IODispatcher
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

private const val CURRENCIES_LISTING_LIMIT = 50

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    @IODispatcher
    private val dispatcher: CoroutineDispatcher,
    private val repository: CurrenciesRepository,
    private val converter: CurrenciesConverter
) : ViewModel() {

    private val _currenciesListing = MutableLiveData<Int>()

    val currencies = _currenciesListing.switchMap { limit ->
        networkResponseLiveData(dispatcher) {
            repository
                .fetchCurrenciesListing(limit)
                .mapResponse(converter::convertCurrenciesListing)
        }.combine(dispatcher) { listing ->
            val ids = converter.convertIds(listing)

            repository.fetchCurrenciesImages(ids)
                .mapResponse(converter::convertCurrenciesImages)
        }
    }

    fun fetchCurrencies() {
        _currenciesListing.postValue(CURRENCIES_LISTING_LIMIT)
    }
}
