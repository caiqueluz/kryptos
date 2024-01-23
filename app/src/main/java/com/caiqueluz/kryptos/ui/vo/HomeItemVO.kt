package com.caiqueluz.kryptos.ui.vo

import androidx.annotation.StringRes
import com.caiqueluz.kryptos.R

sealed class HomeItemVO(
    @StringRes val tabResId: Int
) {

    data object Currencies : HomeItemVO(tabResId = R.string.screen_currencies_title)

    data object News : HomeItemVO(tabResId = R.string.screen_news_title)

    data object Settings : HomeItemVO(tabResId = R.string.screen_settings_title)
}
