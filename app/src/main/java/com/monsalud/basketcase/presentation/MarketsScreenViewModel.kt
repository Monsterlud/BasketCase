package com.monsalud.basketcase.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.domain.BasketCaseRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import timber.log.Timber

class MarketsScreenViewModel(
    private val repository: BasketCaseRepository,
) : ViewModel() {
    private val _markets = MutableStateFlow<List<MarketEntity>>(emptyList())
    val markets: StateFlow<List<MarketEntity>> = _markets.asStateFlow()

    private val _marketUpsertResult = MutableStateFlow<MarketUpsertResult?>(null)
    val marketUpsertResult: StateFlow<MarketUpsertResult?> = _marketUpsertResult.asStateFlow()

    init {
        viewModelScope.launch {
            repository.getAllMarkets().collect { markets ->
                _markets.value = markets
            }
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
                        _markets.value =
                            _markets.value.map {
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

sealed class MarketUpsertResult {
    data class Updated(val market: MarketEntity) : MarketUpsertResult()

    data class Inserted(val market: MarketEntity) : MarketUpsertResult()

    data class Error(val exception: Exception) : MarketUpsertResult()
}