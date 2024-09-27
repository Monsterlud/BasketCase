package com.monsalud.basketcase.domain.model

data class FoodItem(
    val id: Long,
    val name: String,
    val category: FoodCategory = FoodCategory.MISCELLANEOUS,
)

enum class FoodCategory {
    FRUIT, VEGETABLE, DRYGOOD, MEAT, SEAFOOD, DAIRY, BEVERAGE, KITCHENSUPPLY, MISCELLANEOUS;

    fun getFoodCategoryName(): String {
        return when (this) {
            FRUIT -> "Fruit"
            VEGETABLE -> "Vegetable"
            DRYGOOD -> "Dry Good"
            MEAT -> "Meat"
            SEAFOOD -> "Seafood"
            DAIRY -> "Dairy"
            BEVERAGE -> "Beverage"
            KITCHENSUPPLY -> "Kitchen Supply"
            MISCELLANEOUS -> "Miscellaneous"
        }
    }
}
