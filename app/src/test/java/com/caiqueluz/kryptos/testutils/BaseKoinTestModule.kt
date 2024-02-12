package com.caiqueluz.kryptos.testutils

import com.caiqueluz.kryptos.network.ApiBaseUrl
import org.koin.dsl.module

val baseKoinTestModule = module {
    single {
        ApiBaseUrl(
            value = "http://localhost:8080"
        )
    }
}
