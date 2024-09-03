package com.monsalud.basketcase.data.localdatasource.room

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.monsalud.basketcase.domain.model.FoodItem
import com.monsalud.basketcase.domain.model.ItemToPurchase
import com.monsalud.basketcase.domain.model.Market

@Database(version = 1, entities = [FoodItemEntity::class, ItemToPurchaseEntity::class, MarketEntity::class], exportSchema = false)
abstract class BasketCaseDatabase : RoomDatabase() {

    abstract fun basketCaseDAO(): BasketCaseDAO

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