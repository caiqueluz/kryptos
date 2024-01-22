package com.caiqueluz.kryptos

import app.cash.turbine.TurbineContext
import app.cash.turbine.turbineScope
import kotlinx.coroutines.test.TestScope
import kotlinx.coroutines.test.runTest

fun runTurbineTest(
    block: suspend TestScope.(TurbineContext) -> Unit
) = runTest {
    turbineScope {
        block.invoke(this@runTest, this)
    }
}
