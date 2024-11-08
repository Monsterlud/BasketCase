package com.monsalud.basketcase.data.localdatasource.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface PantryItemDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pantryItem: PantryItemEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(pantryItems: List<PantryItemEntity>)

    @Query("SELECT * FROM PantryItemEntity WHERE pantry_item_name = :name AND pantry_item_description = :description LIMIT 1")
    fun getPantryItemByNameAndDescription(
        name: String,
        description: String? = null,
    ): Flow<PantryItemEntity?>

    @Update
    suspend fun update(pantryItem: PantryItemEntity)

    @Delete
    suspend fun delete(pantryItem: PantryItemEntity)

    @Query("DELETE FROM PantryItemEntity")
    fun deleteAll()

    @Query("SELECT * FROM PantryItemEntity")
    fun getAll(): Flow<List<PantryItemEntity>>

    @Query("SELECT * FROM PantryItemEntity WHERE id = :id")
    fun getById(id: Long): Flow<PantryItemEntity>

    @Query("SELECT COUNT(*) FROM PantryItemEntity")
    suspend fun getCount(): Int
}

@Dao
interface MarketDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(market: MarketEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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

    @Query("SELECT COUNT(*) FROM MarketEntity")
    suspend fun getCount(): Int
}

@Dao
interface ItemToPurchaseDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(itemToPurchase: ItemToPurchaseEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(shoppingList: ShoppingListEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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

    @Query("SELECT COUNT(*) FROM ShoppingListEntity")
    suspend fun getCount(): Int
}

@Dao
interface ShoppingListItemAssociationDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(association: ShoppingListItemAssociation)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
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
    suspend fun isItemInList(
        listId: Long,
        itemId: Long,
    ): Boolean

    @Query("SELECT * FROM ShoppingListItemAssociation")
    suspend fun getAllAssociations(): List<ShoppingListItemAssociation>
}
