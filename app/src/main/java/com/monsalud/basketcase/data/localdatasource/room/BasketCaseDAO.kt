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
interface ShoppingListDao {
    @Insert
    suspend fun insert(shoppingList: ShoppingListEntity)

    @Insert
    suspend fun insertAll(shoppingList: List<ShoppingListEntity>)

    @Update
    suspend fun update(shoppingList: ShoppingListEntity)

    @Delete
    suspend fun delete(shoppingList: ShoppingListEntity)

    @Query("DELETE FROM ShoppingListEntity")
    fun deleteAll()

    @Transaction
    @Query("SELECT * FROM ShoppingListEntity")
    fun getAllWithItems(): Flow<List<ShoppingListWithItems>>

    @Transaction
    @Query("SELECT * FROM ShoppingListEntity WHERE id = :id")
    fun getByIdWithItems(id: Long): Flow<ShoppingListWithItems>
}

@Dao
interface ShoppingListItemAssociationDao {
    @Insert
    suspend fun insert(association: ShoppingListItemAssociation)

    @Insert
    suspend fun insertAll(associations: List<ShoppingListItemAssociation>)

    @Delete
    suspend fun delete(association: ShoppingListItemAssociation)

    @Query("DELETE FROM ShoppingListItemAssociation")
    fun deleteAll()

    @Query("SELECT * FROM ShoppingListItemAssociation WHERE shopping_list_id = :listId")
    suspend fun getItemsForList(listId: Long): List<ShoppingListItemAssociation>

    @Query("SELECT * FROM ShoppingListItemAssociation WHERE item_to_purchase_id = :itemId")
    suspend fun getListsForItem(itemId: Long): List<ShoppingListItemAssociation>

    @Query("SELECT EXISTS(SELECT * FROM ShoppingListItemAssociation WHERE shopping_list_id = :listId AND item_to_purchase_id = :itemId)")
    suspend fun isItemInList(listId: Long, itemId: Long): Boolean

    @Query("SELECT * FROM ShoppingListItemAssociation")
    suspend fun getAllAssociations(): List<ShoppingListItemAssociation>
}