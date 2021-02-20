package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.ViewModel
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.NetworkResponseLiveData
import com.caiqueluz.kryptos.network.asLiveData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

private const val CURRENCIES_LISTING_LIMIT = 50

@HiltViewModel
class CurrenciesViewModel @Inject constructor(
    private val repository: CurrenciesRepository,
    private val converter: CurrenciesConverter
) : ViewModel() {

    private val _currenciesListing = NetworkResponseLiveData(
        request = repository::fetchCurrenciesListing,
        transformation = converter::convertCurrenciesListing
    )

    val currenciesListing = _currenciesListing.asLiveData()

    private val _currenciesImages = NetworkResponseLiveData(
        request = repository::fetchCurrenciesImages,
        transformation = converter::convertCurrenciesImages
    )

    val currenciesImages = _currenciesImages.asLiveData()

    fun fetchCurrenciesListing() {
        _currenciesListing.start(CURRENCIES_LISTING_LIMIT)
    }

    fun fetchCurrenciesImages(ids: String) {
        _currenciesImages.start(ids)
    }
}
