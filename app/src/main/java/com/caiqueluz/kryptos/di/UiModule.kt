package com.caiqueluz.kryptos.di

import android.content.Context
import android.content.res.Resources
import com.caiqueluz.kryptos.ui.HomeItemFactory
import com.caiqueluz.kryptos.ui.converter.*
import com.caiqueluz.kryptos.utils.ImageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import java.text.NumberFormat
import java.util.*

@Module
@InstallIn(SingletonComponent::class)
object UiModule {

    @Provides
    fun provideTimeZoneFactory(): TimeZoneFactory =
        TimeZoneFactory()

    @Provides
    fun provideDateFormatFactory(
        timeZoneFactory: TimeZoneFactory
    ): DateFormatFactory = DateFormatFactory(timeZoneFactory)

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

    @Provides
    fun provideHomeItemFactory(
        @ApplicationContext context: Context
    ): HomeItemFactory = HomeItemFactory(context.resources)
}
