package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.ui.converter.CurrenciesConverter
import com.caiqueluz.kryptos.ui.converter.CurrencyQuoteConverter
import com.caiqueluz.kryptos.ui.converter.DateConverter
import com.caiqueluz.kryptos.ui.converter.DateFormatFactory
import com.caiqueluz.kryptos.utils.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun provideDateFormatFactory(): DateFormatFactory =
        DateFormatFactory()

    @Provides
    fun provideDateConverter(
        formatFactory: DateFormatFactory
    ): DateConverter = DateConverter(formatFactory)

    @Provides
    fun provideNumberFormatter(): NumberFormat =
        NumberFormat.getCurrencyInstance(Locale.US)

    @Provides
    fun provideQuoteConverter(
        numberFormatter: NumberFormat,
        dateConverter: DateConverter
    ): CurrencyQuoteConverter = CurrencyQuoteConverter(
        numberFormatter, dateConverter
    )

    @Provides
    fun provideCurrenciesConverter(
        imageLoader: ImageLoader,
        quoteConverter: CurrencyQuoteConverter
    ): CurrenciesConverter = CurrenciesConverter(
        imageLoader, quoteConverter
    )
}
