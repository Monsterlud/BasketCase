package com.monsalud.basketcase.data.localdatasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(
    entities = [
        FoodItemEntity::class,
        ItemToPurchaseEntity::class,
        MarketEntity::class,
        ShoppingListEntity::class,
        ShoppingListItemAssociation::class
    ],
    version = 1,
    exportSchema = false)
abstract class BasketCaseDatabase : RoomDatabase() {

    abstract fun foodItemDao(): FoodItemDao
    abstract fun itemToPurchaseDao(): ItemToPurchaseDao
    abstract fun marketDao(): MarketDao
    abstract fun groceryListDao(): ShoppingListDao
    abstract fun groceryListItemAssociationDao(): ShoppingListItemAssociationDao


    companion object {
        @Volatile
        private var INSTANCE: BasketCaseDatabase? = null

        fun getDatabase(context: Context) : BasketCaseDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context = context.applicationContext,
                    klass = BasketCaseDatabase::class.java,
                    name = "basketcase_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}