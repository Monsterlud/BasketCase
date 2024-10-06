package com.monsalud.basketcase.presentation.navigation

sealed class Screen(val route: String) {

    data object MainScreen : Screen("main_screen")
    data object GroceryBasketScreen : Screen("grocery_basket_screen")
    data object PantryEssentialsScreen : Screen("pantry_essentials_screen")
    data object MarketsScreen : Screen("markets_screen")
}

fun Screen.toRoute(): String {
    return when (this) {
        Screen.MainScreen -> Screen.MainScreen.route
        Screen.GroceryBasketScreen -> Screen.GroceryBasketScreen.route
        Screen.PantryEssentialsScreen -> Screen.PantryEssentialsScreen.route
        Screen.MarketsScreen -> Screen.MarketsScreen.route
    }
}