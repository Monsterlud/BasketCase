package com.monsalud.basketcase.domain.model

class Market(
    val id: Long,
    val marketName: String,
    val marketType: MarketType? = MarketType.OTHER,
    val marketLocation: Pair<Double, Double>? = null,
    val marketAddress: String? = null
)

enum class MarketType {
    GROCERYSTORE, CORNERSTORE, BUTCHERSHOP, FISHMARKET, SPECIALTYMARKET, FARMERSMARKET, BAKERY, OTHER;

    fun getMarketTypeName() : String {
        return when (this) {
            GROCERYSTORE -> "Grocery Store"
            CORNERSTORE -> "Corner Store"
            BUTCHERSHOP -> "Butcher Shop"
            FISHMARKET -> "Fish Market"
            SPECIALTYMARKET -> "Specialty Market"
            FARMERSMARKET -> "Farmers Market"
            BAKERY -> "Bakery"
            OTHER -> "Other"
            else -> "Market"
        }
    }
}
