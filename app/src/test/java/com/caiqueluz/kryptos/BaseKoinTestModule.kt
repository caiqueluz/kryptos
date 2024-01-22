package com.caiqueluz.kryptos

import com.caiqueluz.kryptos.network.ApiBaseUrl
import org.koin.dsl.module

val baseKoinTestModule = module(override = true) {
    single {
        ApiBaseUrl(
            value = "http://localhost:8080"
        )
    }
}
