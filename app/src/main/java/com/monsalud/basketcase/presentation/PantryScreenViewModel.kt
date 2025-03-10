package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.domain.BasketCaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class PantryScreenViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {

    private val _pantryItems = MutableStateFlow<List<PantryItemEntity>>(emptyList())
    val pantryItems: StateFlow<List<PantryItemEntity>> = _pantryItems.asStateFlow()

    private val _pantryItemUpsertResult = MutableStateFlow<PantryItemUpsertResult?>(null)
    val pantryItemUpsertResult: StateFlow<PantryItemUpsertResult?> =
        _pantryItemUpsertResult.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllPantryItems().collect { pantryItems ->
                _pantryItems.value = pantryItems
            }
        }
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
                        _pantryItems.value =
                            _pantryItems.value.map {
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
}

sealed class PantryItemUpsertResult {
    data class Updated(val pantryItem: PantryItemEntity) : PantryItemUpsertResult()

    data class Inserted(val pantryItem: PantryItemEntity) : PantryItemUpsertResult()

    data class Error(val exception: Exception) : PantryItemUpsertResult()
}