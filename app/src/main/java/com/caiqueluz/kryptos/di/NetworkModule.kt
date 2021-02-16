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
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Named("baseUrl")
    fun provideBaseUrl(): String = BuildConfig.BASE_URL

    @Provides
    @Named("apiKey")
    fun providesApiKey(): String = BuildConfig.API_KEY

    @Provides
    fun provideRetrofitBuilder(): Retrofit.Builder =
        Retrofit.Builder()

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
    fun provideApiClientConfig(
        @Named("baseUrl") baseUrl: String,
        retrofitBuilder: Retrofit.Builder,
        converterFactory: GsonConverterFactory,
        okHttpClient: OkHttpClient
    ): ApiClientConfig = ApiClientConfig(
        baseUrl, retrofitBuilder, converterFactory, okHttpClient
    )

    @Provides
    fun provideApiServiceFactory(
        retrofit: Retrofit
    ): ApiServiceFactory = ApiServiceFactory(retrofit)

    @Provides
    fun provideAuthenticationHeaderConfig(
        @Named("apiKey") apiKey: String
    ): AuthenticationHeaderConfig = AuthenticationHeaderConfig(
            header = "HEADER", value = apiKey
        )

    @Provides
    fun provideNetworkAuthenticationInterceptor(
        headerConfig: AuthenticationHeaderConfig
    ): NetworkAuthenticationInterceptor = NetworkAuthenticationInterceptor(
        headerConfig
    )
}
