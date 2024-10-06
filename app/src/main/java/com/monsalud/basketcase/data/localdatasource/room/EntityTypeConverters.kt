package com.monsalud.basketcase.data.localdatasource.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monsalud.basketcase.domain.model.AmountType
import com.monsalud.basketcase.domain.model.Market
import com.monsalud.basketcase.domain.model.MarketType
import com.monsalud.basketcase.domain.model.PantryCategory
import com.monsalud.basketcase.domain.model.PantryItem

class EntityTypeConverters {
    @TypeConverter
    fun fromPantryCategory(value: PantryCategory): String {
        return value.getPantryCategoryName()
    }

    @TypeConverter
    fun toPantryCategory(value: String): PantryCategory {
        return when (value) {
            "Fruit" -> PantryCategory.FRUIT
            "Vegetable" -> PantryCategory.VEGETABLE
            "Pantry" -> PantryCategory.PANTRY
            "Meat" -> PantryCategory.MEAT
            "Poultry" -> PantryCategory.POULTRY
            "Seafood" -> PantryCategory.SEAFOOD
            "Dairy" -> PantryCategory.DAIRY
            "Baked Good" -> PantryCategory.BAKEDGOOD
            "Beverage" -> PantryCategory.BEVERAGE
            "Kitchen Supply" -> PantryCategory.KITCHENSUPPLY
            else -> PantryCategory.MISCELLANEOUS
        }
    }

    @TypeConverter
    fun fromMarketType(value: MarketType): String {
        return value.getMarketTypeName()
    }

    @TypeConverter
    fun toMarketType(value: String): MarketType {
        return when (value) {
            "Grocery Store" -> MarketType.GROCERYSTORE
            "Corner Store" -> MarketType.CORNERSTORE
            "Butcher Shop" -> MarketType.BUTCHERSHOP
            "Fish Market" -> MarketType.FISHMARKET
            "Specialty Market" -> MarketType.SPECIALTYMARKET
            "Farmers Market" -> MarketType.FARMERSMARKET
            "Bakery" -> MarketType.BAKERY
            "Beverage Store" -> MarketType.BEVERAGESTORE
            "Other" -> MarketType.OTHER
            else -> MarketType.OTHER
        }
    }

    @TypeConverter
    fun fromMarketLocation(location: Pair<Double, Double>): String {
        return "${location.first},${location.second}"
    }

    @TypeConverter
    fun toMarketLocation(locationString: String): Pair<Double, Double> {
        val (lat, lng) = locationString.split(",")
        return Pair(lat.toDouble(), lng.toDouble())
    }

    @TypeConverter
    fun fromPantryItem(pantryItem: PantryItem): String {
        val gson = Gson()
        return gson.toJson(pantryItem)
    }

    @TypeConverter
    fun toPantryItem(pantryItemString: String): PantryItem {
        val gson = Gson()
        val type = object : TypeToken<PantryItem>() {}.type
        return gson.fromJson(pantryItemString, type)
    }

    @TypeConverter
    fun fromMarket(market: Market): String {
        val gson = Gson()
        return gson.toJson(market)
    }

    @TypeConverter
    fun toMarket(marketString: String): Market {
        val gson = Gson()
        val type = object : TypeToken<Market>() {}.type
        return gson.fromJson(marketString, type)
    }

    @TypeConverter
    fun fromAmountType(amountType: AmountType): String {
        return amountType.getAmountTypeName()
    }

    @TypeConverter
    fun toAmountType(value: String): AmountType {
        return when (value) {
            "Each" -> AmountType.EACH
            "Bunch" -> AmountType.BUNCH
            "DZ" -> AmountType.DZ
            "Lb" -> AmountType.LB
            "Oz" -> AmountType.OZ
            "Gram" -> AmountType.GRAM
            "Tablespoon" -> AmountType.TABLESPOON
            "Teaspoon" -> AmountType.TEASPOON
            "HalfTeaspoon" -> AmountType.HALFTEASPOON
            "QuarterTeaspoon" -> AmountType.QUARTERTEASPOON
            "EighthTeaspoon" -> AmountType.EIGTHTEASPOON
            else -> AmountType.EACH
        }
    }
}
