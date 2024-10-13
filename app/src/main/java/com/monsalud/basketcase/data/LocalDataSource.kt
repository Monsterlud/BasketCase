package com.monsalud.basketcase.data

import com.monsalud.basketcase.data.localdatasource.datastore.BasketCaseDataStore
import com.monsalud.basketcase.data.localdatasource.room.ItemToPurchaseEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListItemAssociation
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

interface LocalDataSource {

    // PantryItemEntity
    suspend fun insertPantryItem(pantryItem: PantryItemEntity)
    suspend fun insertAllPantryItems(pantryItem: List<PantryItemEntity>)
    suspend fun updatePantryItem(pantryItem: PantryItemEntity)
    suspend fun deletePantryItem(pantryItem: PantryItemEntity)
    suspend fun deleteAllPantryItems()
    fun getAllPantryItems(): Flow<List<PantryItemEntity>>
    fun getPantryItemById(id: Long): Flow<PantryItemEntity>
    suspend fun getPantryItemByNameAndDescription(name: String, description: String? = null): Flow<PantryItemEntity?>

    // MarketEntity
    suspend fun insertMarket(market: MarketEntity)
    suspend fun insertAllMarkets(markets: List<MarketEntity>)
    suspend fun updateMarket(market: MarketEntity)
    suspend fun deleteMarket(market: MarketEntity)
    suspend fun deleteAllMarkets()
    fun getAllMarkets(): Flow<List<MarketEntity>>
    fun getMarketById(id: Long): Flow<MarketEntity>

    // ItemToPurchaseEntity
    suspend fun insertItemToPurchase(itemToPurchase: ItemToPurchaseEntity)
    suspend fun insertAllItemsToPurchase(itemsToPurchase: List<ItemToPurchaseEntity>)
    suspend fun updateItemToPurchase(itemToPurchase: ItemToPurchaseEntity)
    suspend fun deleteItemToPurchase(itemToPurchase: ItemToPurchaseEntity)
    fun deleteAllItemsToPurchase()
    fun getAllItemsToPurchase(): Flow<List<ItemToPurchaseEntity>>
    fun getItemToPurchaseById(id: Long): Flow<ItemToPurchaseEntity>

    // ShoppingListEntity (using ShoppingListEntity in DAO)
    suspend fun insertShoppingList(shoppingList: ShoppingListEntity)
    suspend fun insertAllShoppingLists(shoppingLists: List<ShoppingListEntity>)
    suspend fun updateShoppingList(shoppingList: ShoppingListEntity)
    suspend fun deleteShoppingList(shoppingList: ShoppingListEntity)
    fun deleteAllShoppingLists()
    fun getAllShoppingListsWithItems(): Flow<List<ShoppingListWithItems>>
    fun getShoppingListByIdWithItems(id: Long): Flow<ShoppingListWithItems>

    // ShoppingListItemAssociation
    suspend fun insertShoppingListItemAssociation(association: ShoppingListItemAssociation)
    suspend fun insertAllShoppingListItemAssociations(associations: List<ShoppingListItemAssociation>)
    suspend fun deleteShoppingListItemAssociation(association: ShoppingListItemAssociation)
    fun deleteAllShoppingListItemAssociations()
    suspend fun getItemsForShoppingList(listId: Long): List<ShoppingListItemAssociation>
    suspend fun getShoppingListsForItem(itemId: Long): List<ShoppingListItemAssociation>
    suspend fun isItemInShoppingList(listId: Long, itemId: Long): Boolean
    suspend fun getAllShoppingListItemAssociations(): List<ShoppingListItemAssociation>

    // DataStore
    suspend fun getUserPreferencesFlow(): Flow<BasketCaseDataStore.UserPreferences>

    suspend fun updateHasSeenOnboardingInstructions(hasSeen: Boolean)
    suspend fun updateHasSeenShoppingListInstructions(hasSeen: Boolean)
    suspend fun updateHasSeenBasketInstructions(hasSeen: Boolean)
    suspend fun updateHasSeenPantryInstructions(hasSeen: Boolean)
    suspend fun updateHasSeenMarketInstructions(hasSeen: Boolean)
}