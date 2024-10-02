package com.monsalud.basketcase.domain.model

data class FoodItem(
    val id: Long,
    val name: String,
    val category: FoodCategory = FoodCategory.MISCELLANEOUS,
)

enum class FoodCategory {
    FRUIT, VEGETABLE, DRYGOOD, MEAT, POULTRY, SEAFOOD, DAIRY, BEVERAGE, KITCHENSUPPLY, BAKESHOP, MISCELLANEOUS;

    fun getFoodCategoryName(): String {
        return when (this) {
            FRUIT -> "Fruit"
            VEGETABLE -> "Vegetable"
            DRYGOOD -> "Dry Good"
            MEAT -> "Meat"
            POULTRY -> "Poultry"
            SEAFOOD -> "Seafood"
            DAIRY -> "Dairy"
            BEVERAGE -> "Beverage"
            KITCHENSUPPLY -> "Kitchen Supply"
            BAKESHOP -> "Bake Shop"
            MISCELLANEOUS -> "Miscellaneous"
        }
    }
}
