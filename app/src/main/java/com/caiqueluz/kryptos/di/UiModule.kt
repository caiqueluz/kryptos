package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.ui.HomeItemFactory
import com.caiqueluz.kryptos.ui.converter.*
import com.caiqueluz.kryptos.ui.viewmodel.CurrenciesViewModel
import com.caiqueluz.kryptos.ui.viewmodel.HomeViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.qualifier.named
import org.koin.dsl.module
import java.text.NumberFormat
import java.util.*

val uiModule = module {
    single {
        TimeZoneFactory()
    }

    single {
        DateFormatFactory(
            timeZoneFactory = get()
        )
    }

    single {
        DateConverter(
            formatFactory = get()
        )
    }

    single {
        NumberFormat.getCurrencyInstance(Locale.US)
    }

    single {
        CurrencyQuoteConverter(
            numberFormatter = get(),
            dateConverter = get()
        )
    }

    single {
        CurrencyItemConverter(
            imageLoader = get()
        )
    }

    single {
        CurrenciesConverter(
            quoteConverter = get(),
            itemConverter = get()
        )
    }

    single {
        androidContext().resources
    }

    single {
        HomeItemFactory(
            resources = get()
        )
    }

    single {
        CurrenciesIdsConverter()
    }

    viewModel {
        HomeViewModel(
            factory = get()
        )
    }

    viewModel {
        CurrenciesViewModel(
            dispatcher = get(qualifier = named(IO_DISPATCHER)),
            repository = get(),
            idsConverter = get(),
            currenciesConverter = get()
        )
    }
}
