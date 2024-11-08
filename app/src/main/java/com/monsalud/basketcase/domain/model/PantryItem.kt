package com.monsalud.basketcase.domain.model

data class PantryItem(
    val id: Long,
    val name: String,
    val category: PantryCategory = PantryCategory.MISCELLANEOUS,
)

enum class PantryCategory {
    FRUIT,
    VEGETABLE,
    PANTRY,
    MEAT,
    POULTRY,
    SEAFOOD,
    DAIRY,
    BAKEDGOOD,
    BEVERAGE,
    KITCHENSUPPLY,
    MISCELLANEOUS,
    ;

    fun getPantryCategoryName(): String {
        return when (this) {
            FRUIT -> "Fruit"
            VEGETABLE -> "Vegetable"
            PANTRY -> "Pantry"
            MEAT -> "Meat"
            POULTRY -> "Poultry"
            SEAFOOD -> "Seafood"
            DAIRY -> "Dairy"
            BAKEDGOOD -> "Baked Good"
            BEVERAGE -> "Beverage"
            KITCHENSUPPLY -> "Kitchen Supply"
            MISCELLANEOUS -> "Miscellaneous"
        }
    }
}
