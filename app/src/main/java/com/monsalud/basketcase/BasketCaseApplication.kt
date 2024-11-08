package com.monsalud.basketcase

import android.app.Application
import com.monsalud.basketcase.data.localdatasource.room.BasketCaseDatabase
import com.monsalud.basketcase.di.appModule
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import timber.log.Timber

class BasketCaseApplication : Application() {
    private val applicationScope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()

        Timber.plant(Timber.DebugTree())

        startKoin {
            androidContext(this@BasketCaseApplication)
            modules(appModule)
        }

        initializeDatabase()
    }

    private fun initializeDatabase() {
        applicationScope.launch {
            val database = BasketCaseDatabase.getDatabase(this@BasketCaseApplication)
            database.populateInitialData()
        }
    }
}
