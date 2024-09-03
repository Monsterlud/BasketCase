package com.monsalud.basketcase.di

import android.app.Application
import androidx.room.Room
import com.monsalud.basketcase.data.localdatasource.room.BasketCaseDAO
import com.monsalud.basketcase.data.localdatasource.room.BasketCaseDatabase
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {
    val moduleInstance = AppModule()

    viewModel { BasketCaseViewModel() }

    single { provideDatabase(get()) }
    single { provideDao(get()) }

}
    fun provideDatabase(application: Application): BasketCaseDatabase {
        return Room.databaseBuilder(
            application,
            BasketCaseDatabase::class.java,
            AppModule.BASKETCASE_DATABASE
        ).build()
    }

    fun provideDao(database: BasketCaseDatabase): BasketCaseDAO {
        return database.basketCaseDAO()
    }

class AppModule {
    companion object {
        const val BASKETCASE_DATABASE = "pokepedia_database"

    }
}
