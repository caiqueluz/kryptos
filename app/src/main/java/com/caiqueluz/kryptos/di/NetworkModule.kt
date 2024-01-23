package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.BuildConfig
import com.caiqueluz.kryptos.network.ApiBaseUrl
import com.caiqueluz.kryptos.network.ApiClientConfig
import com.caiqueluz.kryptos.network.ApiServiceFactory
import com.caiqueluz.kryptos.network.AuthenticationHeaderConfig
import com.caiqueluz.kryptos.network.NetworkAuthenticationInterceptor
import com.caiqueluz.kryptos.network.OkHttpClientFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

val networkModule = module {
    single {
        Retrofit.Builder()
    }

    single<Converter.Factory> {
        GsonConverterFactory.create()
    }

    single {
        OkHttpClient.Builder()
    }

    singleOf(::ApiServiceFactory)

    single {
        AuthenticationHeaderConfig(
            header = "X-CMC_PRO_API_KEY",
            value = BuildConfig.API_KEY
        )
    }

    singleOf(::NetworkAuthenticationInterceptor)

    single {
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    singleOf(::OkHttpClientFactory)

    single {
        get<OkHttpClientFactory>().create()
    }

    single {
        ApiBaseUrl(
            value = BuildConfig.BASE_URL
        )
    }

    singleOf(::ApiClientConfig)

    single {
        get<ApiClientConfig>().create()
    }
}
