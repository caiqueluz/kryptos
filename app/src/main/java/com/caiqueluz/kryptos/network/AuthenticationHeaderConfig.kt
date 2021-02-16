package com.caiqueluz.kryptos.network

import javax.inject.Inject

class AuthenticationHeaderConfig @Inject constructor(
    val header: String,
    val value: String
)
