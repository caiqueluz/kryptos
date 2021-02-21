package com.caiqueluz.kryptos.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.switchMap
import com.caiqueluz.kryptos.data.CurrenciesRepository
import com.caiqueluz.kryptos.network.networkResponseLiveData
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

    val currenciesListing = _currenciesListing.switchMap { limit ->
        networkResponseLiveData(
            dispatcher = dispatcher,
            request = { repository.fetchCurrenciesListing(limit) },
            transformation = converter::convertCurrenciesListing
        )
    }

    private val _currenciesImages = MutableLiveData<String>()

    val currenciesImages = _currenciesImages.switchMap { urls ->
        networkResponseLiveData(
            dispatcher = dispatcher,
            request = { repository.fetchCurrenciesImages(urls) },
            transformation = converter::convertCurrenciesImages
        )
    }

    fun fetchCurrenciesListing() {
        _currenciesListing.postValue(CURRENCIES_LISTING_LIMIT)
    }

    fun fetchCurrenciesImages(ids: String) {
        _currenciesImages.postValue(ids)
    }
}
