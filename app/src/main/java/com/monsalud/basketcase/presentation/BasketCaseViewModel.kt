package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.domain.BasketCaseRepository
import com.monsalud.basketcase.domain.model.FoodItem
import com.monsalud.basketcase.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

class BasketCaseViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.MainScreen)
    val currentScreen = _currentScreen.asStateFlow()

    private val _foodItems = MutableStateFlow<List<FoodItemEntity>>(emptyList())
    val foodItems: StateFlow<List<FoodItemEntity>> = _foodItems.asStateFlow()

    private val _markets = MutableStateFlow<List<MarketEntity>>(emptyList())
    val markets: StateFlow<List<MarketEntity>> = _markets.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                repository.getAllFoodItems(),
                repository.getAllMarkets()
            ) {
                items, markets ->
                _foodItems.value = items
                _markets.value = markets
            }.collect {}
        }
    }

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}