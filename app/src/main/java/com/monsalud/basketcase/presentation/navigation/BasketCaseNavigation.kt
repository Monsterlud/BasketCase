package com.monsalud.basketcase.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.screens.FoodItemsScreen
import com.monsalud.basketcase.presentation.screens.MainScreen
import com.monsalud.basketcase.presentation.screens.MarketsScreen
import com.monsalud.basketcase.presentation.screens.GroceryBasketScreen
import org.koin.androidx.compose.koinViewModel
import timber.log.Timber

@Composable
fun BasketCaseNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues,
    onScreenChange: (Screen) -> Unit,
) {
    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
        modifier = Modifier.padding(innerPadding)
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen()
            onScreenChange(Screen.MainScreen)
        }
        composable(route = Screen.FoodItemsScreen.route) {
            FoodItemsScreen()
            onScreenChange(Screen.FoodItemsScreen)
        }
        composable(route = Screen.MarketsScreen.route) {
            MarketsScreen()
            onScreenChange(Screen.MarketsScreen)
        }
        composable(route = Screen.GroceryBasketScreen.route) {
            GroceryBasketScreen()
            onScreenChange(Screen.GroceryBasketScreen)
        }
    }
}