package com.monsalud.basketcase.data.localdatasource.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.monsalud.basketcase.domain.model.AmountType
import com.monsalud.basketcase.domain.model.FoodCategory
import com.monsalud.basketcase.domain.model.FoodItem
import com.monsalud.basketcase.domain.model.Market
import com.monsalud.basketcase.domain.model.MarketType

class EntityTypeConverters {
    @TypeConverter
    fun fromFoodCategory(value: FoodCategory): String {
        return value.getFoodCategoryName()
    }

    @TypeConverter
    fun toFoodCategory(value: String): FoodCategory {
        return when (value) {
            "Fruit" -> FoodCategory.FRUIT
            "Vegetable" -> FoodCategory.VEGETABLE
            "Pantry" -> FoodCategory.PANTRY
            "Meat" -> FoodCategory.MEAT
            "Poultry" -> FoodCategory.POULTRY
            "Seafood" -> FoodCategory.SEAFOOD
            "Dairy" -> FoodCategory.DAIRY
            "Baked Good" -> FoodCategory.BAKEDGOOD
            "Beverage" -> FoodCategory.BEVERAGE
            "Kitchen Supply" -> FoodCategory.KITCHENSUPPLY
            else -> FoodCategory.MISCELLANEOUS
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
    fun fromFoodItem(foodItem: FoodItem): String {
        val gson = Gson()
        return gson.toJson(foodItem)
    }

    @TypeConverter
    fun toFoodItem(foodItemString: String): FoodItem {
        val gson = Gson()
        val type = object : TypeToken<FoodItem>() {}.type
        return gson.fromJson(foodItemString, type)
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
