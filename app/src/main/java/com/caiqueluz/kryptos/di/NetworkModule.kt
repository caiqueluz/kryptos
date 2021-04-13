package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.BuildConfig
import com.caiqueluz.kryptos.network.*
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
        ApiServiceFactory(get())
    }

    single {
        AuthenticationHeaderConfig(
            header = "X-CMC_PRO_API_KEY", value = BuildConfig.API_KEY
        )
    }

    single {
        NetworkAuthenticationInterceptor(get())
    }

    single {
        HttpLoggingInterceptor().setLevel(
            HttpLoggingInterceptor.Level.BODY
        )
    }

    single {
        OkHttpClientFactory(get(), get(), get())
    }

    single {
        get<OkHttpClientFactory>().create()
    }

    single {
        ApiClientConfig(
            BuildConfig.BASE_URL, get(), get(), get()
        )
    }

    single {
        get<ApiClientConfig>().create()
    }
}
