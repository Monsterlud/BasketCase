package com.monsalud.basketcase

import android.app.Application
import com.monsalud.basketcase.di.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BasketCaseApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@BasketCaseApplication)
            modules(appModule)
        }
    }
}