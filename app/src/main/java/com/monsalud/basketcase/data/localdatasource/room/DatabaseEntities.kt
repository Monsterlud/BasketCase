package com.monsalud.basketcase.data.localdatasource.room

import androidx.room.ColumnInfo
import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.Junction
import androidx.room.PrimaryKey
import androidx.room.Relation
import androidx.room.TypeConverters
import com.monsalud.basketcase.domain.model.AmountType
import com.monsalud.basketcase.domain.model.FoodCategory
import com.monsalud.basketcase.domain.model.MarketType

/** Basic Entities **/

@Entity
@TypeConverters(EntityTypeConverters::class)
data class FoodItemEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "food_name")
    var foodName: String,

    @ColumnInfo(name = "food_description")
    var foodDescription: String? = null,

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
    var marketLocation: Pair<Double, Double>? = null,

    @ColumnInfo(name = "market_address")
    var marketAddress: String? = null
)

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = FoodItemEntity::class,
            parentColumns = ["id"],
            childColumns = ["food_item_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = MarketEntity::class,
            parentColumns = ["id"],
            childColumns = ["market_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
@TypeConverters(EntityTypeConverters::class)
data class ItemToPurchaseEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "food_item_id")
    var foodItemId: Long,

    @ColumnInfo(name = "amount_to_purchase")
    var amountToPurchase: Double,

    @ColumnInfo(name = "amount_type")
    var amountType: AmountType,

    @ColumnInfo(name = "market_id")
    var marketId: Long,
)

/** Grocery Lists **/

@Entity
data class ShoppingListEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Long,

    @ColumnInfo(name = "list_name")
    var listName: String,

    @ColumnInfo(name = "creation_date")
    var creationDate: Long = System.currentTimeMillis()
)

/** Associations
 * This join table creates a many-to-many relationship between GroceryListEntity and ItemToPurchaseEntity.
 * It allows a single GroceryListEntity to have multiple ItemToPurchaseEntity and vice versa.
 */

@Entity(
    foreignKeys = [
        ForeignKey(
            entity = ShoppingListEntity::class,
            parentColumns = ["id"],
            childColumns = ["shopping_list_id"],
            onDelete = ForeignKey.CASCADE
        ),
        ForeignKey(
            entity = ItemToPurchaseEntity::class,
            parentColumns = ["id"],
            childColumns = ["item_to_purchase_id"],
            onDelete = ForeignKey.CASCADE
        )
    ]
)
data class ShoppingListItemAssociation(
    @PrimaryKey(autoGenerate = true)
    var id: Long = 0,

    @ColumnInfo(name = "shopping_list_id")
    var groceryListId: Long,

    @ColumnInfo(name = "item_to_purchase_id")
    var itemToPurchaseId: Long
)

/** Data Retrieval **/

data class ShoppingListWithItems(
    @Embedded val shoppingList: ShoppingListEntity,
    @Relation(
        parentColumn = "id",
        entityColumn = "id",
        associateBy = Junction(
            value = ShoppingListItemAssociation::class,
            parentColumn = "shopping_list_id",
            entityColumn = "item_to_purchase_id"
        )
    )
    val items: List<ItemToPurchaseEntity>
)
