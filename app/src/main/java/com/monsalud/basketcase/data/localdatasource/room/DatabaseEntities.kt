package com.monsalud.basketcase.data.localdatasource.room

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.monsalud.basketcase.domain.model.AmountType
import com.monsalud.basketcase.domain.model.FoodCategory
import com.monsalud.basketcase.domain.model.FoodItem
import com.monsalud.basketcase.domain.model.Market
import com.monsalud.basketcase.domain.model.MarketType

@Entity
@TypeConverters(EntityTypeConverters::class)
data class FoodItemEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "food_name")
    var foodName: String,

    @ColumnInfo(name = "food_category")
    var foodCategory: FoodCategory
)

@Entity
@TypeConverters(EntityTypeConverters::class)
data class MarketEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "market_name")
    var marketName: String,

    @ColumnInfo(name = "market_type")
    var marketType: MarketType,

    @ColumnInfo(name = "market_location")
    var marketLocation: Pair<Double, Double>,

    @ColumnInfo(name = "market_address")
    var marketAddress: String
)

@Entity
@TypeConverters(EntityTypeConverters::class)
data class ItemToPurchaseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "food_item")
    var foodItem: FoodItem,

    @ColumnInfo(name = "market")
    var market: Market,

    @ColumnInfo(name = "amount_to_purchase")
    var amountToPurchase: Double,

    @ColumnInfo(name = "amount_type")
    var amountType: AmountType
)
