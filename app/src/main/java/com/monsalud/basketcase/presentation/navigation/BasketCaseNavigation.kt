package com.monsalud.basketcase.presentation.navigation

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.screens.FoodCategoryListScreen
import com.monsalud.basketcase.presentation.screens.FoodItemsScreen
import com.monsalud.basketcase.presentation.screens.MainScreen
import com.monsalud.basketcase.presentation.screens.MarketsScreen
import com.monsalud.basketcase.presentation.screens.ShoppingListScreen
import org.koin.androidx.compose.getViewModel

@Composable
fun BasketCaseNavigation(
    innerPadding: PaddingValues,
    onScreenChange: (Screen) -> Unit,
) {
    val navController = rememberNavController()
    val viewModel: BasketCaseViewModel = getViewModel()

    NavHost(
        navController = navController,
        startDestination = Screen.MainScreen.route,
    ) {
        composable(route = Screen.MainScreen.route) {
            MainScreen()
            onScreenChange(Screen.MainScreen)
        }
        composable(route = Screen.FoodCategoriesScreen.route) {
            FoodCategoryListScreen()
            onScreenChange(Screen.FoodCategoriesScreen)
        }
        composable(route = Screen.FoodItemsScreen.route) {
            FoodItemsScreen()
            onScreenChange(Screen.FoodItemsScreen)
        }
        composable(route = Screen.MarketsScreen.route) {
            MarketsScreen()
            onScreenChange(Screen.MarketsScreen)
        }
        composable(route = Screen.ShoppingListScreen.route) {
            ShoppingListScreen()
            onScreenChange(Screen.ShoppingListScreen)
        }
    }
}