package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.asNetworkResponse
import com.caiqueluz.kryptos.network.mapResponse
import com.caiqueluz.kryptos.ui.combine
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.caiqueluz.kryptos.ui.networkResponseLiveData
import kotlinx.coroutines.CoroutineDispatcher

private const val CURRENCIES_LISTING_LIMIT = 50

class CurrenciesViewModel(
    private val dispatcher: CoroutineDispatcher,
    private val repository: CurrenciesRepository,
    private val idsConverter: CurrenciesIdsConverter,
    private val currenciesConverter: CurrenciesConverter
) : ViewModel() {

    private val _currencies = MutableLiveData<Int>()
    val currencies = _currencies.switchMap { limit ->
        networkResponseLiveData(dispatcher) {
            repository
                .fetchCurrenciesListing(limit)
                .asNetworkResponse()
        }.combine(dispatcher) { listing ->
            val ids = idsConverter.convertIds(listing)

            repository.fetchCurrenciesWithImages(ids)
                .mapResponse { images ->
                    currenciesConverter.convertCurrencies(listing, images)
                }
        }
    }

    fun fetchCurrencies() {
        _currencies.postValue(CURRENCIES_LISTING_LIMIT)
    }
}
