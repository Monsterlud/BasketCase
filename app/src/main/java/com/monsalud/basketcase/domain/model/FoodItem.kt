package com.monsalud.basketcase.domain.model

data class FoodItem(
    val id: Long,
    val name: String,
    val category: FoodCategory = FoodCategory.MISCELLANEOUS,
)

enum class FoodCategory {
    FRUIT, VEGETABLE, PANTRY, MEAT, POULTRY, SEAFOOD, DAIRY, BAKESHOP, BEVERAGE, KITCHENSUPPLY, MISCELLANEOUS;

    fun getFoodCategoryName(): String {
        return when (this) {
            FRUIT -> "Fruit"
            VEGETABLE -> "Vegetable"
            PANTRY -> "Pantry"
            MEAT -> "Meat"
            POULTRY -> "Poultry"
            SEAFOOD -> "Seafood"
            DAIRY -> "Dairy"
            BAKESHOP -> "Bake Shop"
            BEVERAGE -> "Beverage"
            KITCHENSUPPLY -> "Kitchen Supply"
            MISCELLANEOUS -> "Miscellaneous"
        }
    }
}
