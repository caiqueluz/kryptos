package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.R
import com.caiqueluz.kryptos.ui.view.CurrenciesFragment
import com.caiqueluz.kryptos.ui.vo.HomeItemVO

class HomeItemFactory {

    fun create(): List<HomeItemVO> = listOf(
        HomeItemVO(
            R.string.screen_currencies_title, CurrenciesFragment()
        ),
        HomeItemVO(
            R.string.screen_news_title, CurrenciesFragment()
        ),
        HomeItemVO(
            R.string.screen_settings_title, CurrenciesFragment()
        )
    )
}
