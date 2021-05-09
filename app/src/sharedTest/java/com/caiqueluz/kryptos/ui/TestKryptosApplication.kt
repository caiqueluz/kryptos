package com.caiqueluz.kryptos.ui

import android.app.Application
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.loadKoinModules
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.core.module.Module
import org.koin.dsl.module

class TestKryptosApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        injectTestDependencies()
    }

    private fun injectTestDependencies() {
        stopKoin()

        startKoin {
            androidLogger()
            androidContext(this@TestKryptosApplication)
            modules(listOf())
        }
    }
}

fun injectTestModule(builder: Module.() -> Unit) {
    val module = module(
        createdAtStart = true, override = true, moduleDeclaration = builder
    )

    loadKoinModules(module)
}
