package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.domain.BasketCaseRepository
import com.monsalud.basketcase.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch
import timber.log.Timber

class BasketCaseViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.MainScreen)
    val currentScreen = _currentScreen.asStateFlow()

    private val _shoppingLists = MutableStateFlow<List<ShoppingListEntity>>(emptyList())
    val shoppingLists: StateFlow<List<ShoppingListEntity>> = _shoppingLists.asStateFlow()

    private val _pantryItems = MutableStateFlow<List<PantryItemEntity>>(emptyList())
    val pantryItems: StateFlow<List<PantryItemEntity>> = _pantryItems.asStateFlow()

    private val _markets = MutableStateFlow<List<MarketEntity>>(emptyList())
    val markets: StateFlow<List<MarketEntity>> = _markets.asStateFlow()

    private val _pantryItemUpsertResult = MutableStateFlow<PantryItemUpsertResult?>(null)
    val pantryItemUpsertResult: StateFlow<PantryItemUpsertResult?> = _pantryItemUpsertResult.asStateFlow()

    private val _marketUpsertResult = MutableStateFlow<MarketUpsertResult?>(null)
    val marketUpsertResult: StateFlow<MarketUpsertResult?> = _marketUpsertResult.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getAllPantryItems(),
                repository.getAllMarkets()
            ) { items, markets ->
                _pantryItems.value = items
                _markets.value = markets
            }.collect {}
        }
    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    fun upsertPantryItemToDatabase(pantryItem: PantryItemEntity) {
        viewModelScope.launch {
            try {
                if (pantryItem.id != 0L) {
                    // Update the existing item in the database
                    Timber.d("Updating existing item: $pantryItem.id, ${pantryItem.pantryItemName}, ${pantryItem.pantryItemDescription}")
                    repository.updatePantryItem(pantryItem)
                    _pantryItemUpsertResult.value = PantryItemUpsertResult.Updated(pantryItem)

                    repository.getPantryItemById(pantryItem.id).collect { updatedItem ->
                        _pantryItems.value = _pantryItems.value.map {
                            if (it.id == updatedItem.id) updatedItem else it
                        }
                    }
                } else {
                    // Insert the new item into the database
                    repository.insertPantryItem(pantryItem)
                    _pantryItemUpsertResult.value = PantryItemUpsertResult.Inserted(pantryItem)
                }
            } catch (e: Exception) {
                _pantryItemUpsertResult.value = PantryItemUpsertResult.Error(e)
            }
        }
    }

    fun deletePantryItemFromDatabase(pantryItem: PantryItemEntity) {
        viewModelScope.launch {
            repository.deletePantryItem(pantryItem)
        }
    }

    fun upsertMarketToDatabase(market: MarketEntity) {
        viewModelScope.launch {
            try {
                if (market.id != 0L) {
                    // Update the existing market in the database
                    Timber.d("Updating existing market: $market.id, ${market.marketName}, ${market.marketAddress}")
                    repository.updateMarket(market)
                    _marketUpsertResult.value = MarketUpsertResult.Updated(market)

                    repository.getMarketById(market.id).collect { updatedMarket ->
                        _markets.value = _markets.value.map {
                            if (it.id == updatedMarket.id) updatedMarket else it
                        }
                    }
                } else {
                    // Insert the new market into the database
                    repository.insertMarket(market)
                    _marketUpsertResult.value = MarketUpsertResult.Inserted(market)
                }
            } catch (e: Exception) {
                _marketUpsertResult.value = MarketUpsertResult.Error(e)
            }
        }
    }

    fun deleteMarketFromDatabase(market: MarketEntity) {
        viewModelScope.launch {
            repository.deleteMarket(market)
        }
    }
}

sealed class PantryItemUpsertResult {
    data class Updated(val pantryItem: PantryItemEntity) : PantryItemUpsertResult()
    data class Inserted(val pantryItem: PantryItemEntity) : PantryItemUpsertResult()
    data class Error(val exception: Exception) : PantryItemUpsertResult()
}

sealed class MarketUpsertResult {
    data class Updated(val market: MarketEntity) : MarketUpsertResult()
    data class Inserted(val market: MarketEntity) : MarketUpsertResult()
    data class Error(val exception: Exception) : MarketUpsertResult()

}