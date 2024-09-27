package com.monsalud.basketcase

import android.app.Application
import com.monsalud.basketcase.data.localdatasource.DummyData.foodItems
import com.monsalud.basketcase.data.localdatasource.DummyData.markets
import com.monsalud.basketcase.data.localdatasource.DummyData.shoppingLists
import com.monsalud.basketcase.data.localdatasource.room.BasketCaseDatabase
import com.monsalud.basketcase.di.appModule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
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

        // todo: remove this dummy data after setting up ui
        GlobalScope.launch(Dispatchers.IO) {
            val database = BasketCaseDatabase.getDatabase(this@BasketCaseApplication)

            val foodItemDao = database.foodItemDao()
            val itemToPurchaseDao = database.itemToPurchaseDao()
            val marketDao = database.marketDao()
            val groceryListDao = database.groceryListDao()
            val groceryListItemAssociationDao = database.groceryListItemAssociationDao()

            foodItemDao.deleteAll()
            itemToPurchaseDao.deleteAll()
            marketDao.deleteAll()
            groceryListDao.deleteAll()
            groceryListItemAssociationDao.deleteAll()

            foodItemDao.insertAll(foodItems = foodItems)
            marketDao.insertAll(markets = markets)
            groceryListDao.insertAll(groceryLists = shoppingLists)
        }
    }
}