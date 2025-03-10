package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.domain.BasketCaseRepository
import com.monsalud.basketcase.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

class BasketCaseViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {
    private val _currentScreen = MutableStateFlow<Screen>(Screen.MainScreen)
    val currentScreen = _currentScreen.asStateFlow()

    private val _shoppingLists = MutableStateFlow<List<ShoppingListEntity>>(emptyList())
    val shoppingLists: StateFlow<List<ShoppingListEntity>> = _shoppingLists.asStateFlow()

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}
