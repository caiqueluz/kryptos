package com.caiqueluz.kryptos.ui

import android.content.res.Resources
import com.caiqueluz.kryptos.R
import com.caiqueluz.kryptos.ui.view.CurrenciesFragment
import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import javax.inject.Inject

class HomeItemFactory @Inject constructor(
    private val resources: Resources
) {

    fun create(): List<HomeItemVO> = listOf(
        HomeItemVO(
            resources.getString(R.string.screen_currencies_title), CurrenciesFragment()
        ),
        HomeItemVO(
            resources.getString(R.string.screen_news_title), CurrenciesFragment()
        ),
        HomeItemVO(
            resources.getString(R.string.screen_settings_title), CurrenciesFragment()
        )
    )
}
