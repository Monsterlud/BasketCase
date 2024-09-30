package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import com.monsalud.basketcase.domain.BasketCaseRepository
import com.monsalud.basketcase.presentation.navigation.Screen
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class BasketCaseViewModel(
    private val basketCaseRepository: BasketCaseRepository,
) : ViewModel() {

    private val _currentScreen = MutableStateFlow<Screen>(Screen.MainScreen)
    val currentScreen = _currentScreen.asStateFlow()

    fun setCurrentScreen(screen: Screen) {
        _currentScreen.value = screen
    }
}