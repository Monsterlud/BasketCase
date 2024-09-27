package com.monsalud.basketcase.data.localdatasource

import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.data.localdatasource.room.GroceryListEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.domain.model.FoodCategory
import com.monsalud.basketcase.domain.model.MarketType

object DummyData {
    // todo: remove this dummy data after setting up ui

    val markets = listOf(
        MarketEntity(
            id = 1,
            marketName = "Whole Foods",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 2,
            marketName = "Trader Joe's",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 3,
            marketName = "Cure Farm",
            marketType = MarketType.FARMERSMARKET
        ),
        MarketEntity(
            id = 4,
            marketName = "Moxie Bread Company",
            marketType = MarketType.BAKERY
        ),
        MarketEntity(
            id = 5,
            marketName = "Blackbelly Market",
            marketType = MarketType.BUTCHERSHOP
        ),
        MarketEntity(
            id = 6,
            marketName = "H Mart",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 7,
            marketName = "Costco",
            marketType = MarketType.OTHER
        )
    )

    val foodItems = listOf(
        FoodItemEntity(
            id = 1,
            foodName = "Bananas",
            foodCategory = FoodCategory.FRUIT
        ),
        FoodItemEntity(
            id = 2,
            foodName = "Anchovies",
            foodCategory = FoodCategory.DRYGOOD
        ),
        FoodItemEntity(
            id = 3,
            foodName = "AP Flour",
            foodCategory = FoodCategory.DRYGOOD
        ),
        FoodItemEntity(
            id = 4,
            foodName = "Salmon, Filet",
            foodCategory = FoodCategory.SEAFOOD
        ),
        FoodItemEntity(
            id = 5,
            foodName = "Milk, Whole",
            foodCategory = FoodCategory.DAIRY
        ),
        FoodItemEntity(
            id = 6,
            foodName = "Hot Sauce, Tabasco",
            foodCategory = FoodCategory.DRYGOOD
        ),
        FoodItemEntity(
            id = 7,
            foodName = "Cheese, Cheddar",
            foodCategory = FoodCategory.DAIRY
        ),
        FoodItemEntity(
            id = 8,
            foodName = "Garlic, Whole",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 9,
            foodName = "Bread, Whole Grain",
            foodCategory = FoodCategory.BAKESHOP
        ),
        FoodItemEntity(
            id = 10,
            foodName = "Bread, Sourdough",
            foodCategory = FoodCategory.BAKESHOP
        ),
        FoodItemEntity(
            id = 11,
            foodName = "Broccolini",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 12,
            foodName = "Broccoli Rabe",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 13,
            foodName = "Kale, Dinosaur",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 14,
            foodName = "Cheese, Parmesan",
            foodCategory = FoodCategory.DAIRY
        )
    )

    val shoppingLists = listOf(
        GroceryListEntity(1, "Weekly Groceries", System.currentTimeMillis()),
        GroceryListEntity(2, "Goodbye Natalie...", System.currentTimeMillis()),
    )
}