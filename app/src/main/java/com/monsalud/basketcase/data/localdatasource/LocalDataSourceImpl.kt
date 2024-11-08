package com.monsalud.basketcase.data.localdatasource

import com.monsalud.basketcase.data.LocalDataSource
import com.monsalud.basketcase.data.localdatasource.datastore.BasketCaseDataStore
import com.monsalud.basketcase.data.localdatasource.room.ItemToPurchaseDao
import com.monsalud.basketcase.data.localdatasource.room.ItemToPurchaseEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketDao
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.data.localdatasource.room.PantryItemDao
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListDao
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListItemAssociation
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListItemAssociationDao
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListWithItems
import kotlinx.coroutines.flow.Flow

class LocalDataSourceImpl(
    private val pantryItemDao: PantryItemDao,
    private val marketDao: MarketDao,
    private val itemToPurchaseDao: ItemToPurchaseDao,
    private val shoppingListDao: ShoppingListDao,
    private val shoppingListItemAssociationDao: ShoppingListItemAssociationDao,
    private val dataStore: BasketCaseDataStore,
) : LocalDataSource {
    /** PantryItemEntity */
    override suspend fun insertPantryItem(pantryItem: PantryItemEntity) = pantryItemDao.insert(pantryItem)

    override suspend fun insertAllPantryItems(pantryItems: List<PantryItemEntity>) = pantryItemDao.insertAll(pantryItems)

    override suspend fun updatePantryItem(pantryItem: PantryItemEntity) = pantryItemDao.update(pantryItem)

    override suspend fun deletePantryItem(pantryItem: PantryItemEntity) = pantryItemDao.delete(pantryItem)

    override suspend fun deleteAllPantryItems() = pantryItemDao.deleteAll()

    override fun getAllPantryItems(): Flow<List<PantryItemEntity>> = pantryItemDao.getAll()

    override fun getPantryItemById(id: Long): Flow<PantryItemEntity> = pantryItemDao.getById(id)

    override suspend fun getPantryItemByNameAndDescription(
        name: String,
        description: String?,
    ): Flow<PantryItemEntity?> {
        return if (description == null) {
            pantryItemDao.getPantryItemByNameAndDescription(name)
        } else {
            pantryItemDao.getPantryItemByNameAndDescription(name, description)
        }
    }

    /** MarketEntity */
    override suspend fun insertMarket(market: MarketEntity) = marketDao.insert(market)

    override suspend fun insertAllMarkets(markets: List<MarketEntity>) = marketDao.insertAll(markets)

    override suspend fun updateMarket(market: MarketEntity) = marketDao.update(market)

    override suspend fun deleteMarket(market: MarketEntity) = marketDao.delete(market)

    override suspend fun deleteAllMarkets() = marketDao.deleteAll()

    override fun getAllMarkets(): Flow<List<MarketEntity>> = marketDao.getAll()

    override fun getMarketById(id: Long): Flow<MarketEntity> = marketDao.getById(id)

    /** ItemToPurchaseEntity */
    override suspend fun insertItemToPurchase(itemToPurchase: ItemToPurchaseEntity) = itemToPurchaseDao.insert(itemToPurchase)

    override suspend fun insertAllItemsToPurchase(itemsToPurchase: List<ItemToPurchaseEntity>) =
        itemToPurchaseDao.insertAll(itemsToPurchase)

    override suspend fun updateItemToPurchase(itemToPurchase: ItemToPurchaseEntity) = itemToPurchaseDao.update(itemToPurchase)

    override suspend fun deleteItemToPurchase(itemToPurchase: ItemToPurchaseEntity) = itemToPurchaseDao.delete(itemToPurchase)

    override fun deleteAllItemsToPurchase() = itemToPurchaseDao.deleteAll()

    override fun getAllItemsToPurchase(): Flow<List<ItemToPurchaseEntity>> = itemToPurchaseDao.getAll()

    override fun getItemToPurchaseById(id: Long): Flow<ItemToPurchaseEntity> = itemToPurchaseDao.getById(id)

    /** ShoppingListEntity */
    override suspend fun insertShoppingList(shoppingList: ShoppingListEntity) = shoppingListDao.insert(shoppingList)

    override suspend fun insertAllShoppingLists(shoppingLists: List<ShoppingListEntity>) = shoppingListDao.insertAll(shoppingLists)

    override suspend fun updateShoppingList(shoppingList: ShoppingListEntity) = shoppingListDao.update(shoppingList)

    override suspend fun deleteShoppingList(shoppingList: ShoppingListEntity) = shoppingListDao.delete(shoppingList)

    override fun deleteAllShoppingLists() = shoppingListDao.deleteAll()

    override fun getAllShoppingListsWithItems(): Flow<List<ShoppingListWithItems>> = shoppingListDao.getAllWithItems()

    override fun getShoppingListByIdWithItems(id: Long): Flow<ShoppingListWithItems> = shoppingListDao.getByIdWithItems(id)

    /** ShoppingListItemAssociation */
    override suspend fun insertShoppingListItemAssociation(association: ShoppingListItemAssociation) =
        shoppingListItemAssociationDao.insert(association)

    override suspend fun insertAllShoppingListItemAssociations(associations: List<ShoppingListItemAssociation>) =
        shoppingListItemAssociationDao.insertAll(associations)

    override suspend fun deleteShoppingListItemAssociation(association: ShoppingListItemAssociation) =
        shoppingListItemAssociationDao.delete(association)

    override fun deleteAllShoppingListItemAssociations() = shoppingListItemAssociationDao.deleteAll()

    override suspend fun getItemsForShoppingList(listId: Long): List<ShoppingListItemAssociation> =
        shoppingListItemAssociationDao.getItemsForList(listId)

    override suspend fun getShoppingListsForItem(itemId: Long): List<ShoppingListItemAssociation> =
        shoppingListItemAssociationDao.getListsForItem(itemId)

    override suspend fun isItemInShoppingList(
        listId: Long,
        itemId: Long,
    ): Boolean = shoppingListItemAssociationDao.isItemInList(listId, itemId)

    override suspend fun getAllShoppingListItemAssociations(): List<ShoppingListItemAssociation> =
        shoppingListItemAssociationDao.getAllAssociations()

    /** Preferences DataStore Methods */

    override suspend fun getUserPreferencesFlow(): Flow<BasketCaseDataStore.UserPreferences> {
        return dataStore.preferencesFlow
    }

    override suspend fun updateHasSeenOnboardingInstructions(hasSeen: Boolean) {
        dataStore.updateHasSeenOnboardingInstructions(hasSeen)
    }

    override suspend fun updateHasSeenShoppingListInstructions(hasSeen: Boolean) {
        dataStore.updateHasSeenShoppingListInstructions(hasSeen)
    }

    override suspend fun updateHasSeenBasketInstructions(hasSeen: Boolean) {
        dataStore.updateHasSeenBasketInstructions(hasSeen)
    }

    override suspend fun updateHasSeenPantryInstructions(hasSeen: Boolean) {
        dataStore.updateHasSeenPantryInstructions(hasSeen)
    }

    override suspend fun updateHasSeenMarketInstructions(hasSeen: Boolean) {
        dataStore.updateHasSeenMarketInstructions(hasSeen)
    }
}
