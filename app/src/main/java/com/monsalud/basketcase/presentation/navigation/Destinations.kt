package com.monsalud.basketcase.presentation.navigation

sealed class Screen(val route: String) {

    data object MainScreen : Screen("main_screen")
    data object FoodItemsScreen : Screen("food_items_screen")
    data object FoodCategoriesScreen : Screen("food_categories_screen")
    data object MarketsScreen : Screen("markets_screen")
    data object ShoppingListScreen : Screen("shopping_list_screen")
}