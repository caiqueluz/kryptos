package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.ui.HomeItemFactory
import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrenciesIdsConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyItemConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import com.caiqueluz.kryptos.ui.converter.DateConverter
import com.caiqueluz.kryptos.ui.converter.DateFormatFactory
import com.caiqueluz.kryptos.ui.converter.TimeZoneFactory
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import java.text.NumberFormat
import java.util.Locale

val uiModule = module {
    singleOf(::TimeZoneFactory)
    singleOf(::DateFormatFactory)
    singleOf(::DateConverter)

    single {
        NumberFormat.getCurrencyInstance(Locale.US)
    }

    singleOf(::CurrencyQuoteConverter)
    singleOf(::CurrencyItemConverter)
    singleOf(::CurrenciesConverter)

    single {
        androidContext().resources
    }

    singleOf(::HomeItemFactory)
    singleOf(::CurrenciesIdsConverter)

    viewModelOf(::HomeViewModel)
    viewModelOf(::CurrenciesViewModel)
}
