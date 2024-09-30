package com.monsalud.basketcase.data

import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.data.localdatasource.room.ItemToPurchaseEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListItemAssociation
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListWithItems
import com.monsalud.basketcase.domain.BasketCaseRepository
import kotlinx.coroutines.flow.Flow

class BasketCaseRepositoryImpl(
    private val localDataSource: LocalDataSource,
    private val remoteDataSource: RemoteDataSource,
) : BasketCaseRepository {

    // FoodItemEntity
    override suspend fun insertFoodItem(foodItem: FoodItemEntity) =
        localDataSource.insertFoodItem(foodItem)

    override suspend fun insertAllFoodItems(foodItems: List<FoodItemEntity>) =
        localDataSource.insertAllFoodItems(foodItems)

    override suspend fun updateFoodItem(foodItem: FoodItemEntity) =
        localDataSource.updateFoodItem(foodItem)


    override suspend fun deleteFoodItem(foodItem: FoodItemEntity) =
        localDataSource.deleteFoodItem(foodItem)

    override suspend fun deleteAllFoodItems() = localDataSource.deleteAllFoodItems()

    override fun getAllFoodItems(): Flow<List<FoodItemEntity>> = localDataSource.getAllFoodItems()

    override fun getFoodItemById(id: Long): Flow<FoodItemEntity> =
        localDataSource.getFoodItemById(id)

    // MarketEntity
    override suspend fun insertMarket(market: MarketEntity) = localDataSource.insertMarket(market)

    override suspend fun insertAllMarkets(markets: List<MarketEntity>) =
        localDataSource.insertAllMarkets(markets)

    override suspend fun updateMarket(market: MarketEntity) = localDataSource.updateMarket(market)

    override suspend fun deleteMarket(market: MarketEntity) = localDataSource.deleteMarket(market)

    override suspend fun deleteAllMarkets() = localDataSource.deleteAllMarkets()

    override fun getAllMarkets(): Flow<List<MarketEntity>> = localDataSource.getAllMarkets()

    override fun getMarketById(id: Long): Flow<MarketEntity> = localDataSource.getMarketById(id)

    // ItemToPurchaseEntity
    override suspend fun insertItemToPurchase(itemToPurchase: ItemToPurchaseEntity) =
        localDataSource.insertItemToPurchase(itemToPurchase)

    override suspend fun insertAllItemsToPurchase(itemsToPurchase: List<ItemToPurchaseEntity>) =
        localDataSource.insertAllItemsToPurchase(itemsToPurchase)

    override suspend fun updateItemToPurchase(itemToPurchase: ItemToPurchaseEntity) =
        localDataSource.updateItemToPurchase(itemToPurchase)

    override suspend fun deleteItemToPurchase(itemToPurchase: ItemToPurchaseEntity) =
        localDataSource.deleteItemToPurchase(itemToPurchase)

    override fun deleteAllItemsToPurchase() = localDataSource.deleteAllItemsToPurchase()

    override fun getAllItemsToPurchase(): Flow<List<ItemToPurchaseEntity>> =
        localDataSource.getAllItemsToPurchase()

    override fun getItemToPurchaseById(id: Long): Flow<ItemToPurchaseEntity> =
        localDataSource.getItemToPurchaseById(id)

    // ShoppingListEntity
    override suspend fun insertShoppingList(shoppingList: ShoppingListEntity) =
        localDataSource.insertShoppingList(shoppingList)

    override suspend fun insertAllShoppingLists(shoppingLists: List<ShoppingListEntity>) =
        localDataSource.insertAllShoppingLists(shoppingLists)

    override suspend fun updateShoppingList(shoppingList: ShoppingListEntity) =
        localDataSource.updateShoppingList(shoppingList)

    override suspend fun deleteShoppingList(shoppingList: ShoppingListEntity) =
        localDataSource.deleteShoppingList(shoppingList)

    override fun deleteAllShoppingLists() = localDataSource.deleteAllShoppingLists()

    override fun getAllShoppingListsWithItems(): Flow<List<ShoppingListWithItems>> =
        localDataSource.getAllShoppingListsWithItems()

    override fun getShoppingListByIdWithItems(id: Long): Flow<ShoppingListWithItems> =
        localDataSource.getShoppingListByIdWithItems(id)

    // ShoppingListItemAssociation
    override suspend fun insertShoppingListItemAssociation(association: ShoppingListItemAssociation) =
        localDataSource.insertShoppingListItemAssociation(association)

    override suspend fun insertAllShoppingListItemAssociations(associations: List<ShoppingListItemAssociation>) =
        localDataSource.insertAllShoppingListItemAssociations(associations)

    override suspend fun deleteShoppingListItemAssociation(association: ShoppingListItemAssociation) =
        localDataSource.deleteShoppingListItemAssociation(association)

    override fun deleteAllShoppingListItemAssociations() =
        localDataSource.deleteAllShoppingListItemAssociations()

    override suspend fun getItemsForShoppingList(listId: Long): List<ShoppingListItemAssociation> =
        localDataSource.getItemsForShoppingList(listId)

    override suspend fun getShoppingListsForItem(itemId: Long): List<ShoppingListItemAssociation> =
        localDataSource.getShoppingListsForItem(itemId)

    override suspend fun isItemInShoppingList(listId: Long, itemId: Long): Boolean =
        localDataSource.isItemInShoppingList(listId, itemId)

    override suspend fun getAllShoppingListItemAssociations(): List<ShoppingListItemAssociation> =
        localDataSource.getAllShoppingListItemAssociations()
}