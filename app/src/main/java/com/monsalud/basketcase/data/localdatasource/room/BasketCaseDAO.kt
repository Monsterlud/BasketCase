package com.monsalud.basketcase.data.localdatasource.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface FoodItemDao {
    @Insert
    suspend fun insert(foodItem: FoodItemEntity)

    @Insert
    suspend fun insertAll(foodItems: List<FoodItemEntity>)

    @Update
    suspend fun update(foodItem: FoodItemEntity)

    @Delete
    suspend fun delete(foodItem: FoodItemEntity)

    @Query("DELETE FROM FoodItemEntity")
    fun deleteAll()

    @Query("SELECT * FROM FoodItemEntity")
    fun getAll(): Flow<List<FoodItemEntity>>

    @Query("SELECT * FROM FoodItemEntity WHERE id = :id")
    fun getById(id: Long): Flow<FoodItemEntity>
}

@Dao
interface MarketDao {
    @Insert
    suspend fun insert(market: MarketEntity)

    @Insert
    suspend fun insertAll(markets: List<MarketEntity>)

    @Update
    suspend fun update(market: MarketEntity)

    @Delete
    suspend fun delete(market: MarketEntity)

    @Query("DELETE FROM MarketEntity")
    fun deleteAll()

    @Query("SELECT * FROM MarketEntity")
    fun getAll(): Flow<List<MarketEntity>>

    @Query("SELECT * FROM MarketEntity WHERE id = :id")
    fun getById(id: Long): Flow<MarketEntity>
}

@Dao
interface ItemToPurchaseDao {
    @Insert
    suspend fun insert(itemToPurchase: ItemToPurchaseEntity)

    @Insert
    suspend fun insertAll(itemsToPurchase: List<ItemToPurchaseEntity>)

    @Update
    suspend fun update(itemToPurchase: ItemToPurchaseEntity)

    @Delete
    suspend fun delete(itemToPurchase: ItemToPurchaseEntity)

    @Query("DELETE FROM ItemToPurchaseEntity")
    fun deleteAll()

    @Query("SELECT * FROM ItemToPurchaseEntity")
    fun getAll(): Flow<List<ItemToPurchaseEntity>>

    @Query("SELECT * FROM ItemToPurchaseEntity WHERE id = :id")
    fun getById(id: Long): Flow<ItemToPurchaseEntity>
}

@Dao
interface GroceryListDao {
    @Insert
    suspend fun insert(groceryList: GroceryListEntity)

    @Insert
    suspend fun insertAll(groceryLists: List<GroceryListEntity>)

    @Update
    suspend fun update(groceryList: GroceryListEntity)

    @Delete
    suspend fun delete(groceryList: GroceryListEntity)

    @Query("DELETE FROM GroceryListEntity")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM GroceryListEntity")
    fun getAllWithItems(): Flow<List<GroceryListWithItems>>

    @Transaction
    @Query("SELECT * FROM GroceryListEntity WHERE id = :id")
    fun getByIdWithItems(id: Long): Flow<GroceryListWithItems>
}

@Dao
interface GroceryListItemAssociationDao {
    @Insert
    suspend fun insert(association: GroceryListItemAssociation)

    @Insert
    suspend fun insertAll(associations: List<GroceryListItemAssociation>)

    @Delete
    suspend fun delete(association: GroceryListItemAssociation)

    @Query("DELETE FROM GroceryListItemAssociation")
    fun deleteAll()
}