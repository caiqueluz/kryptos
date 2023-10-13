package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.BuildConfig
import com.caiqueluz.kryptos.network.ApiClientConfig
import com.caiqueluz.kryptos.network.ApiServiceFactory
import com.caiqueluz.kryptos.network.AuthenticationHeaderConfig
import com.caiqueluz.kryptos.network.NetworkAuthenticationInterceptor
import com.caiqueluz.kryptos.network.OkHttpClientFactory
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
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

    single {
        ApiServiceFactory(
            retrofit = get()
        )
    }

    single {
        AuthenticationHeaderConfig(
            header = "X-CMC_PRO_API_KEY",
            value = BuildConfig.API_KEY
        )
    }

    single {
        NetworkAuthenticationInterceptor(
            headerConfig = get()
        )
    }

    single {
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    single {
        OkHttpClientFactory(
            okHttpBuilder = get(),
            authenticationInterceptor = get(),
            httpLoggingInterceptor = get()
        )
    }

    single {
        get<OkHttpClientFactory>().create()
    }

    single {
        ApiClientConfig(
            baseUrl = BuildConfig.BASE_URL,
            retrofitBuilder = get(),
            converterFactory = get(),
            okHttpClient = get()
        )
    }

    single {
        get<ApiClientConfig>().create()
    }
}
