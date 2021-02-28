package com.caiqueluz.kryptos.network

import retrofit2.Retrofit
import javax.inject.Inject

class ApiServiceFactory @Inject constructor(
    private val retrofit: Retrofit
) {

    fun <TYPE> create(api: Class<TYPE>): TYPE = retrofit.create(api)
}
