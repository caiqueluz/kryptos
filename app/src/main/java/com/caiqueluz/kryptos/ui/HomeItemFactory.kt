package com.caiqueluz.kryptos.ui

import com.caiqueluz.kryptos.ui.vo.HomeItemVO
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.Currencies
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.News
import com.caiqueluz.kryptos.ui.vo.HomeItemVO.Settings

class HomeItemFactory {

    fun create(): List<HomeItemVO> =
        listOf(
            Currencies,
            News,
            Settings
        )
}
