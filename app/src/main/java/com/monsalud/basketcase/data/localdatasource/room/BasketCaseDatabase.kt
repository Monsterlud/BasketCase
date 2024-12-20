package com.monsalud.basketcase.data.localdatasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monsalud.basketcase.data.localdatasource.DefaultData.markets
import com.monsalud.basketcase.data.localdatasource.DefaultData.pantryItems
import com.monsalud.basketcase.data.localdatasource.DefaultData.shoppingLists
import kotlinx.coroutines.flow.first
import timber.log.Timber

@Database(
    entities = [
        PantryItemEntity::class,
        ItemToPurchaseEntity::class,
        MarketEntity::class,
        ShoppingListEntity::class,
        ShoppingListItemAssociation::class,
    ],
    version = 1,
    exportSchema = false,
)
abstract class BasketCaseDatabase : RoomDatabase() {
    abstract fun pantryItemDao(): PantryItemDao

    abstract fun itemToPurchaseDao(): ItemToPurchaseDao

    abstract fun marketDao(): MarketDao

    abstract fun shoppingListDao(): ShoppingListDao

    abstract fun shoppingListItemAssociationDao(): ShoppingListItemAssociationDao

    suspend fun populateInitialData() {
        if (marketDao().getCount() == 0) {
            marketDao().insertAll(markets = markets)
        }
        if (pantryItemDao().getCount() == 0) {
            pantryItemDao().insertAll(pantryItems = pantryItems)
        }
        if (shoppingListDao().getCount() == 0) {
            shoppingListDao().insertAll(shoppingList = shoppingLists)
        }

        val insertedMarkets = marketDao().getAll()
        insertedMarkets.first().forEach { market ->
            Timber.d("Inserted market: ${market.marketName}, Type: ${market.marketType}")
        }
    }

    companion object {
        @Volatile
        private var instance: BasketCaseDatabase? = null

        fun getDatabase(context: Context): BasketCaseDatabase {
            val tempInstance = instance
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val synchronizedInstance =
                    Room.databaseBuilder(
                        context = context.applicationContext,
                        klass = BasketCaseDatabase::class.java,
                        name = "basketcase_database",
                    )
                        .fallbackToDestructiveMigration()
                        .build()
                instance = synchronizedInstance
                return synchronizedInstance
            }
        }
    }
}
