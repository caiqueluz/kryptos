package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.NetworkResponse
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.caiqueluz.kryptos.ui.vo.CurrenciesVO
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

private const val CURRENCIES_LISTING_LIMIT = 50

class CurrenciesViewModel(
    private val repository: CurrenciesRepository,
    private val idsConverter: CurrenciesIdsConverter,
    private val currenciesConverter: CurrenciesConverter
) : ViewModel() {

    private val _currencies = MutableStateFlow<NetworkResponse<CurrenciesVO>>(
        value = NetworkResponse.Loading
    )

    val currencies = _currencies.asStateFlow()

    fun onScreenStarted() {
        loadCurrencies()
    }

    fun onErrorModalTryAgainButtonClicked() {
        loadCurrencies()
    }

    private fun loadCurrencies() {
        viewModelScope.launch {
            _currencies.update { NetworkResponse.Loading }

            val response = try {
                val listing = repository.fetchCurrenciesListing(limit = CURRENCIES_LISTING_LIMIT)

                val images = repository.fetchCurrenciesWithImages(
                    ids = idsConverter.convertIds(listing)
                )

                NetworkResponse.Content(
                    content = currenciesConverter.convertCurrencies(listing, images)
                )
            } catch (throwable: Throwable) {
                NetworkResponse.Error
            }

            _currencies.update { response }
        }
    }
}
