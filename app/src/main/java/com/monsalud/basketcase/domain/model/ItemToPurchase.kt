package com.monsalud.basketcase.domain.model

data class ItemToPurchase(
    val id: Long,
    val item: FoodItem,
    val market: Market,
    val amount: Double,
    val amountType: AmountType
)

enum class AmountType {
    EACH, BUNCH, DZ, LB, OZ, GRAM, TABLESPOON, TEASPOON, HALFTEASPOON, QUARTERTEASPOON, EIGTHTEASPOON;

    fun getAmountTypeName(): String {
        return when (this) {
            EACH -> "Each"
            BUNCH -> "Bunch"
            DZ -> "DZ"
            LB -> "Lb"
            OZ -> "Oz"
            GRAM -> "Gram"
            TABLESPOON -> "Tablespoon"
            TEASPOON -> "Teaspoon"
            HALFTEASPOON -> "HalfTeaspoon"
            QUARTERTEASPOON -> "QuarterTeaspoon"
            EIGTHTEASPOON -> "EighthTeaspoon"
        }
    }
}

