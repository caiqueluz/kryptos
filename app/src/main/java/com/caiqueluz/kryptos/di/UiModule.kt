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
        DateFormatFactory(get())
    }

    single {
        DateConverter(get())
    }

    single {
        NumberFormat.getCurrencyInstance(Locale.US)
    }

    single {
        CurrencyQuoteConverter(get(), get())
    }

    single {
        CurrencyItemConverter(get())
    }

    single {
        CurrenciesConverter(get(), get())
    }

    single {
        androidContext().resources
    }

    single {
        HomeItemFactory(get())
    }

    single {
        CurrenciesIdsConverter()
    }

    viewModel {
        HomeViewModel(get())
    }

    viewModel {
        CurrenciesViewModel(
            get(qualifier = named(IO_DISPATCHER)), get(), get(), get()
        )
    }
}
