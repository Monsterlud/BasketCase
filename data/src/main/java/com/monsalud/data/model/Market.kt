package com.monsalud.data.model

class Market(
    val id: Int,
    val marketName: String,
    val marketType: MarketType,
    val location: Double,
)

enum class MarketType {
    GROCERYSTORE, BODEGA, BUTCHERSHOP, FISHMARKET, SPECIALTYMARKET, FARMERSMARKET;

    fun getMarketTypeName() : String {
        return when (this) {
            GROCERYSTORE -> "Grocery Store"
            BODEGA -> "Bodega"
            BUTCHERSHOP -> "Butcher Shop"
            FISHMARKET -> "Fish Market"
            SPECIALTYMARKET -> "Specialty Market"
            FARMERSMARKET -> "Farmers Market"
            else -> "Market"
        }
    }
}
