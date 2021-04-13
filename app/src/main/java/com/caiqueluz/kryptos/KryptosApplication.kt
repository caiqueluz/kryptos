package com.caiqueluz.kryptos

import android.app.Application
import com.caiqueluz.kryptos.di.appModules
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class KryptosApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        injectDependencies()
    }

    private fun injectDependencies() {
        startKoin {
            androidLogger()
            androidContext(this@KryptosApplication)
            modules(appModules)
        }
    }
}
