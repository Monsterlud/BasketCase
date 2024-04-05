package com.monsalud.data.model

data class FoodItem(
    val id: Int,
    val name: String,
    val category: Category,
)

enum class Category {
    FRUIT, VEGETABLE, DRYGOOD, MEAT, SEAFOOD, DAIRY, BEVERAGE, KITCHENSUPPLY;

    fun getCategoryName(): String {
        return when (this) {
            FRUIT -> "Fruit"
            VEGETABLE -> "Vegetable"
            DRYGOOD -> "Dry Good"
            MEAT -> "Meat"
            SEAFOOD -> "Seafood"
            DAIRY -> "Dairy"
            BEVERAGE -> "Beverage"
            KITCHENSUPPLY -> "Kitchen Supply"
            else -> "Market"
        }
    }
}
