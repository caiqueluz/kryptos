package com.caiqueluz.kryptos.ui.view

import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.Currencies
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.News
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.Settings

fun HomeItemVO.toHomeTabFragment() =
    when (this) {
        is Currencies -> CurrenciesFragment()
        is News -> CurrenciesFragment()
        is Settings -> CurrenciesFragment()
    }
