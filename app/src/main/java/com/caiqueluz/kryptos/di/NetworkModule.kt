package com.caiqueluz.kryptos.di

import com.caiqueluz.kryptos.BuildConfig
import com.caiqueluz.kryptos.network.*
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()

    @Provides
    fun provideRetrofitInstance(
        apiClientConfig: ApiClientConfig
    ): Retrofit = apiClientConfig.create()

    @Provides
    fun provideConverterFactory(): Converter.Factory =
        GsonConverterFactory.create()

    @Provides
    fun provideOkHttpClientBuilder(): OkHttpClient.Builder =
        OkHttpClient.Builder()

    @Provides
    fun provideOkHttpClientFactory(
        okHttpBuilder: OkHttpClient.Builder,
        authenticationInterceptor: NetworkAuthenticationInterceptor
    ): OkHttpClientFactory =
        OkHttpClientFactory(
            okHttpBuilder, authenticationInterceptor
        )

    @Provides
    fun provideOkHttpClient(
        okHttpClientFactory: OkHttpClientFactory
    ): OkHttpClient = okHttpClientFactory.create()

    @Provides
    fun provideGsonConverterFactory(): GsonConverterFactory =
        GsonConverterFactory.create()

    @Provides
    fun provideApiClientConfig(
        retrofitBuilder: Retrofit.Builder,
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): ApiClientConfig = ApiClientConfig(
        BuildConfig.BASE_URL, retrofitBuilder, converterFactory, okHttpClient
    )

    @Provides
    fun provideApiServiceFactory(
        retrofit: Retrofit
    ): ApiServiceFactory = ApiServiceFactory(retrofit)

    @Provides
    fun provideAuthenticationHeaderConfig(): AuthenticationHeaderConfig =
        AuthenticationHeaderConfig(
            header = "X-CMC_PRO_API_KEY", value = BuildConfig.API_KEY
        )

    @Provides
    fun provideNetworkAuthenticationInterceptor(
        headerConfig: AuthenticationHeaderConfig
    ): NetworkAuthenticationInterceptor = NetworkAuthenticationInterceptor(
        headerConfig
    )
}
