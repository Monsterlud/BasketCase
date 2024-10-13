package com.monsalud.basketcase.di

import android.app.Application
import androidx.room.Room
import com.monsalud.basketcase.data.BasketCaseRepositoryImpl
import com.monsalud.basketcase.data.LocalDataSource
import com.monsalud.basketcase.data.RemoteDataSource
import com.monsalud.basketcase.data.localdatasource.LocalDataSourceImpl
import com.monsalud.basketcase.data.localdatasource.datastore.BasketCaseDataStore
import com.monsalud.basketcase.data.localdatasource.room.BasketCaseDatabase
import com.monsalud.basketcase.data.localdatasource.room.ItemToPurchaseDao
import com.monsalud.basketcase.data.localdatasource.room.MarketDao
import com.monsalud.basketcase.data.localdatasource.room.PantryItemDao
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListDao
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListItemAssociationDao
import com.monsalud.basketcase.data.remotedatasource.RemoteDataSourceImpl
import com.monsalud.basketcase.domain.BasketCaseRepository
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.bind
import org.koin.dsl.module

val appModule = module {
    val moduleInstance = AppModule()

    viewModel { BasketCaseViewModel(get()) }
    single { BasketCaseRepositoryImpl(get(), get()) } bind BasketCaseRepository::class
    single { LocalDataSourceImpl(get(), get(), get(), get(), get(), get()) } bind LocalDataSource::class
    single { RemoteDataSourceImpl() } bind RemoteDataSource::class

    single { provideDatabase(get()) }
    single { providePantryItemDao(get()) }
    single { provideMarketDao(get()) }
    single { provideItemToPurchaseDao(get()) }
    single { provideGroceryListDao(get()) }
    single { provideGroceryListItemAssociationDaoO(get()) }

    single { BasketCaseDataStore(get()) }
}
    fun provideDatabase(application: Application): BasketCaseDatabase {
        return Room.databaseBuilder(
            application,
            BasketCaseDatabase::class.java,
            AppModule.BASKETCASE_DATABASE
        ).build()
    }

    fun providePantryItemDao(database: BasketCaseDatabase): PantryItemDao {
        return database.pantryItemDao()
    }

    fun provideMarketDao(database: BasketCaseDatabase): MarketDao {
        return database.marketDao()
    }

    fun provideItemToPurchaseDao(database: BasketCaseDatabase): ItemToPurchaseDao {
        return database.itemToPurchaseDao()
    }

    fun provideGroceryListDao(database: BasketCaseDatabase): ShoppingListDao {
        return database.shoppingListDao()
    }

    fun provideGroceryListItemAssociationDaoO(database: BasketCaseDatabase): ShoppingListItemAssociationDao {
        return database.shoppingListItemAssociationDao()
    }



class AppModule {
    companion object {
        const val BASKETCASE_DATABASE = "basketcase_database"

    }
}
