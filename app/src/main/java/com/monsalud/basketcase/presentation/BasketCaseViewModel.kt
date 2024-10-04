package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.domain.BasketCaseRepository
import com.monsalud.basketcase.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import timber.log.Timber

class BasketCaseViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.MainScreen)
    val currentScreen = _currentScreen.asStateFlow()

    private val _shoppingLists = MutableStateFlow<List<ShoppingListEntity>>(emptyList())
    val shoppingLists: StateFlow<List<ShoppingListEntity>> = _shoppingLists.asStateFlow()

    private val _foodItems = MutableStateFlow<List<FoodItemEntity>>(emptyList())
    val foodItems: StateFlow<List<FoodItemEntity>> = _foodItems.asStateFlow()

    private val _markets = MutableStateFlow<List<MarketEntity>>(emptyList())
    val markets: StateFlow<List<MarketEntity>> = _markets.asStateFlow()

    private val _foodItemUpsertResult = MutableStateFlow<FoodItemUpsertResult?>(null)
    val foodItemUpsertResult: StateFlow<FoodItemUpsertResult?> = _foodItemUpsertResult.asStateFlow()

    private val _marketUpsertResult = MutableStateFlow<MarketUpsertResult?>(null)
    val marketUpsertResult: StateFlow<MarketUpsertResult?> = _marketUpsertResult.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getAllFoodItems(),
                repository.getAllMarkets()
            ) { items, markets ->
                _foodItems.value = items
                _markets.value = markets
            }.collect {}
        }
    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }

    fun upsertFoodItemToDatabase(foodItem: FoodItemEntity) {
        viewModelScope.launch {
            try {
                if (foodItem.id != 0L) {
                    // Update the existing item in the database
                    Timber.d("Updating existing item: $foodItem.id, ${foodItem.foodName}, ${foodItem.foodDescription}")
                    repository.updateFoodItem(foodItem)
                    _foodItemUpsertResult.value = FoodItemUpsertResult.Updated(foodItem)

                    repository.getFoodItemById(foodItem.id).collect { updatedItem ->
                        _foodItems.value = _foodItems.value.map {
                            if (it.id == updatedItem.id) updatedItem else it
                        }
                    }
                } else {
                    // Insert the new item into the database
                    repository.insertFoodItem(foodItem)
                    _foodItemUpsertResult.value = FoodItemUpsertResult.Inserted(foodItem)
                }
            } catch (e: Exception) {
                _foodItemUpsertResult.value = FoodItemUpsertResult.Error(e)
            }
        }
    }

    fun deleteFoodItemFromDatabase(foodItem: FoodItemEntity) {
        viewModelScope.launch {
            repository.deleteFoodItem(foodItem)
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

sealed class FoodItemUpsertResult {
    data class Updated(val foodItem: FoodItemEntity) : FoodItemUpsertResult()
    data class Inserted(val foodItem: FoodItemEntity) : FoodItemUpsertResult()
    data class Error(val exception: Exception) : FoodItemUpsertResult()
}

sealed class MarketUpsertResult {
    data class Updated(val market: MarketEntity) : MarketUpsertResult()
    data class Inserted(val market: MarketEntity) : MarketUpsertResult()
    data class Error(val exception: Exception) : MarketUpsertResult()

}