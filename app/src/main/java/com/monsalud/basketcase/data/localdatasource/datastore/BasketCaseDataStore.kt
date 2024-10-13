package com.monsalud.basketcase.data.localdatasource.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.core.IOException
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map


private const val BASKETCASE_PREFERENCES = "basketcase_preferences"
val Context.basketCasePreferencesDatastore: DataStore<Preferences> by preferencesDataStore(
    name = BASKETCASE_PREFERENCES
)

class BasketCaseDataStore(
    private val context: Context
) {
    private val dataStore = context.basketCasePreferencesDatastore

    internal object PreferencesKeys {
        val HAS_SEEN_ONBOARDING_INSTRUCTIONS = booleanPreferencesKey("has_seen_onboarding_instructions")
        val HAS_SEEN_SHOPPING_LIST_INSTRUCTIONS = booleanPreferencesKey("has_seen_shopping_list_instructions")
        val HAS_SEEN_BASKET_INSTRUCTIONS = booleanPreferencesKey("has_seen_basket_instructions")
        val HAS_SEEN_PANTRY_INSTRUCTIONS = booleanPreferencesKey("has_seen_pantry_instructions")
        val HAS_SEEN_MARKET_INSTRUCTIONS = booleanPreferencesKey("has_seen_market_instructions")
    }

    val preferencesFlow = dataStore.data
        .catch { exception ->
            if (exception is IOException) {
                emit(emptyPreferences())
            } else {
                throw exception
            }
        }
        .map { preferences ->
            val hasSeenOnboardingInstructions = preferences[PreferencesKeys.HAS_SEEN_ONBOARDING_INSTRUCTIONS] ?: false
            val hasSeenShoppingListInstructions = preferences[PreferencesKeys.HAS_SEEN_SHOPPING_LIST_INSTRUCTIONS] ?: false
            val hasSeenBasketInstructions = preferences[PreferencesKeys.HAS_SEEN_BASKET_INSTRUCTIONS] ?: false
            val hasSeenPantryInstructions = preferences[PreferencesKeys.HAS_SEEN_PANTRY_INSTRUCTIONS] ?: false
            val hasSeenMarketInstructions = preferences[PreferencesKeys.HAS_SEEN_MARKET_INSTRUCTIONS] ?: false
            UserPreferences(
                hasSeenOnboardingInstructions = hasSeenOnboardingInstructions,
                hasSeenShoppingListInstructions = hasSeenShoppingListInstructions,
                hasSeenBasketInstructions = hasSeenBasketInstructions,
                hasSeenPantryInstructions = hasSeenPantryInstructions,
                hasSeenMarketInstructions = hasSeenMarketInstructions
            )
        }

    data class UserPreferences(
        val hasSeenOnboardingInstructions: Boolean,
        val hasSeenShoppingListInstructions: Boolean,
        val hasSeenBasketInstructions: Boolean,
        val hasSeenPantryInstructions: Boolean,
        val hasSeenMarketInstructions: Boolean,
    )

    suspend fun updateHasSeenOnboardingInstructions(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_SEEN_ONBOARDING_INSTRUCTIONS] = hasSeen
        }
    }

    suspend fun updateHasSeenShoppingListInstructions(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_SEEN_SHOPPING_LIST_INSTRUCTIONS] = hasSeen
        }
    }

    suspend fun updateHasSeenBasketInstructions(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_SEEN_BASKET_INSTRUCTIONS] = hasSeen
        }
    }

    suspend fun updateHasSeenPantryInstructions(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_SEEN_PANTRY_INSTRUCTIONS] = hasSeen
        }
    }

    suspend fun updateHasSeenMarketInstructions(hasSeen: Boolean) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.HAS_SEEN_MARKET_INSTRUCTIONS] = hasSeen
        }
    }
}