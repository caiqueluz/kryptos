package com.caiqueluz.kryptos.ui.vo

import androidx.annotation.StringRes
import com.caiqueluz.kryptos.R

sealed class HomeItemVO(
    @StringRes val tabResId: Int
) {

    object Currencies : HomeItemVO(tabResId = R.string.screen_currencies_title)

    object News : HomeItemVO(tabResId = R.string.screen_news_title)

    object Settings : HomeItemVO(tabResId = R.string.screen_settings_title)
}
