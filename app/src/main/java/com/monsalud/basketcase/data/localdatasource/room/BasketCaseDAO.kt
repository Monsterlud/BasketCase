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
    suspend fun insert(foodItem: FoodItemEntity): Long

    @Update
    suspend fun update(foodItem: FoodItemEntity)

    @Delete
    suspend fun delete(foodItem: FoodItemEntity)

    @Query("SELECT * FROM FoodItemEntity")
    fun getAllFoodItems(): Flow<List<FoodItemEntity>>

    @Query("SELECT * FROM FoodItemEntity WHERE id = :id")
    suspend fun getFoodItemById(id: Long): FoodItemEntity?
}

@Dao
interface MarketDao {
    @Insert
    suspend fun insert(market: MarketEntity): Long

    @Update
    suspend fun update(market: MarketEntity)

    @Delete
    suspend fun delete(market: MarketEntity)

    @Query("SELECT * FROM MarketEntity")
    fun getAllMarkets(): Flow<List<MarketEntity>>

    @Query("SELECT * FROM MarketEntity WHERE id = :id")
    suspend fun getMarketById(id: Long): MarketEntity?
}

@Dao
interface ItemToPurchaseDao {
    @Insert
    suspend fun insert(itemToPurchase: ItemToPurchaseEntity): Long

    @Update
    suspend fun update(itemToPurchase: ItemToPurchaseEntity)

    @Delete
    suspend fun delete(itemToPurchase: ItemToPurchaseEntity)

    @Query("SELECT * FROM ItemToPurchaseEntity")
    fun getAllItemsToPurchase(): Flow<List<ItemToPurchaseEntity>>

    @Query("SELECT * FROM ItemToPurchaseEntity WHERE id = :id")
    suspend fun getItemToPurchaseById(id: Long): ItemToPurchaseEntity?
}

@Dao
interface GroceryListDao {
    @Insert
    suspend fun insert(groceryList: GroceryListEntity): Long

    @Update
    suspend fun update(groceryList: GroceryListEntity)

    @Delete
    suspend fun delete(groceryList: GroceryListEntity)

    @Query("SELECT * FROM GroceryListEntity")
    fun getAllGroceryLists(): Flow<List<GroceryListEntity>>

    @Query("SELECT * FROM GroceryListEntity WHERE id = :id")
    suspend fun getGroceryListById(id: Long): GroceryListEntity?

    @Transaction
    @Query("SELECT * FROM GroceryListEntity")
    fun getGroceryListsWithItems(): Flow<List<GroceryListWithItems>>

    @Transaction
    @Query("SELECT * FROM GroceryListEntity WHERE id = :groceryListId")
    fun getGroceryListWithItems(groceryListId: Long): Flow<GroceryListWithItems>
}

@Dao
interface GroceryListItemAssociationDao {
    @Insert
    suspend fun insert(association: GroceryListItemAssociation): Long

    @Delete
    suspend fun delete(association: GroceryListItemAssociation)

    @Query("DELETE FROM GroceryListItemAssociation WHERE grocery_list_id = :groceryListId AND item_to_purchase_id = :itemToPurchaseId")
    suspend fun deleteAssociation(groceryListId: Long, itemToPurchaseId: Long)

    @Query("SELECT * FROM GroceryListItemAssociation WHERE grocery_list_id = :groceryListId")
    fun getAssociationsForGroceryList(groceryListId: Long): Flow<List<GroceryListItemAssociation>>
}