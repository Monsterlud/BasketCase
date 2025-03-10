package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monsalud.basketcase.data.localdatasource.datastore.BasketCaseDataStore
import com.monsalud.basketcase.domain.BasketCaseRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch

class UserPreferencesViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {
    private val _isLoadingPreferences = MutableStateFlow(true)
    val isLoadingPreferences: StateFlow<Boolean> = _isLoadingPreferences.asStateFlow()

    @OptIn(ExperimentalCoroutinesApi::class)
    val userPreferencesFlow: StateFlow<BasketCaseDataStore.UserPreferences> =
        flow {
            emit(repository.getUserPreferencesFlow())
        }.onEach { _isLoadingPreferences.value = false }
            .flatMapLatest { it }
            .stateIn(
                scope = viewModelScope,
                started = SharingStarted.Eagerly,
                initialValue =
                BasketCaseDataStore.UserPreferences(
                    hasSeenOnboardingInstructions = true,
                    hasSeenShoppingListInstructions = true,
                    hasSeenBasketInstructions = true,
                    hasSeenPantryInstructions = true,
                    hasSeenMarketInstructions = true,
                ),
            )

    fun updateHasSeenOnboardingInstructions(hasSeen: Boolean) {
        viewModelScope.launch {
            repository.updateHasSeenOnboardingInstructions(hasSeen)
        }
    }

    fun updateHasSeenShoppingListInstructions(hasSeen: Boolean) {
        viewModelScope.launch {
            repository.updateHasSeenShoppingListInstructions(hasSeen)
        }
    }

    fun updateHasSeenBasketInstructions(hasSeen: Boolean) {
        viewModelScope.launch {
            repository.updateHasSeenBasketInstructions(hasSeen)
        }
    }

    fun updateHasSeenPantryInstructions(hasSeen: Boolean) {
        viewModelScope.launch {
            repository.updateHasSeenPantryInstructions(hasSeen)
        }
    }

    fun updateHasSeenMarketInstructions(hasSeen: Boolean) {
        viewModelScope.launch {
            repository.updateHasSeenMarketInstructions(hasSeen)
        }
    }

    fun onOnboardingInstructionsSeen() {
        viewModelScope.launch {
            repository.updateHasSeenOnboardingInstructions(true)
        }
    }


}