package com.monsalud.basketcase.data.localdatasource

import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.domain.model.FoodCategory
import com.monsalud.basketcase.domain.model.MarketType

object DefaultData {
    // todo: remove the dummy data after setting up ui. keep only foodItems.

    val markets = listOf(
        MarketEntity(
            id = 1,
            marketName = "Whole Foods Market",
            marketAddress = "2905 Pearl St, Boulder, CO 80301",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 2,
            marketName = "Trader Joe's",
            marketAddress = "1906 28th St, Boulder, CO 80301",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 3,
            marketName = "Cure Organic Farm",
            marketAddress = "7416 Valmont Rd, Boulder, CO 80301",
            marketType = MarketType.FARMERSMARKET
        ),
        MarketEntity(
            id = 4,
            marketName = "Moxie Bread Company",
            marketAddress = "4593 Broadway, Boulder, CO 80304",
            marketType = MarketType.BAKERY
        ),
        MarketEntity(
            id = 5,
            marketName = "Blackbelly Market",
            marketAddress = "1606 Conestoga St Suite 1, Boulder, CO 80301",
            marketType = MarketType.BUTCHERSHOP
        ),
        MarketEntity(
            id = 6,
            marketName = "H Mart",
            marketAddress = "Northview Shopping Center, 5036 W 92nd Ave, Westminster, CO 80031",
            marketType = MarketType.SPECIALTYMARKET
        ),
        MarketEntity(
            id = 7,
            marketName = "Costco",
            marketAddress = "600 Marshall Rd, Superior, CO 80027",
            marketType = MarketType.OTHER
        ),
        MarketEntity(
            id = 8,
            marketName = "Asian Market",
            marketAddress = "2829 28th St, Boulder, CO 80301",
            marketType = MarketType.SPECIALTYMARKET
        ),
        MarketEntity(
            id = 9,
            marketName = "Safeway",
            marketAddress = "3325 28th St, Boulder, CO 80301",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 10,
            marketName = "King Soopers",
            marketAddress = "1650 30th St, Boulder, CO 80301",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 11,
            marketName = "Lucky's Bakehouse",
            marketAddress = "3990 Broadway, Boulder, CO 80304",
            marketType = MarketType.BAKERY
        ),
        MarketEntity(
            id = 12,
            marketName = "Sprouts",
            marketAddress = "2525 Arapahoe Ave Unit E65, Boulder, CO 80302",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 13,
            marketName = "Ideal Market",
            marketAddress = "1275 Alpine Ave., Boulder, CO 80304",
            marketType = MarketType.GROCERYSTORE
        ),
        MarketEntity(
            id = 14,
            marketName = "Boulder Wine Merchant",
            marketAddress = "2690 Broadway, Boulder, CO 80304",
            marketType = MarketType.BEVERAGESTORE
        ),
        MarketEntity(
            id = 15,
            marketName = "Hazel's Beverage World",
            marketAddress = "1955 28th St, Boulder, CO 80301",
            marketType = MarketType.BEVERAGESTORE
        ),
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
            foodCategory = FoodCategory.PANTRY
        ),
        FoodItemEntity(
            id = 3,
            foodName = "AP Flour",
            foodCategory = FoodCategory.PANTRY
        ),
        FoodItemEntity(
            id = 4,
            foodName = "Salmon",
            foodDescription = "Filet",
            foodCategory = FoodCategory.SEAFOOD
        ),
        FoodItemEntity(
            id = 5,
            foodName = "Milk",
            foodDescription = "Skim",
            foodCategory = FoodCategory.DAIRY
        ),
        FoodItemEntity(
            id = 6,
            foodName = "Hot Sauce",
            foodDescription = "Tabasco",
            foodCategory = FoodCategory.PANTRY
        ),
        FoodItemEntity(
            id = 7,
            foodName = "Cheese",
            foodDescription = "Cheddar",
            foodCategory = FoodCategory.DAIRY
        ),
        FoodItemEntity(
            id = 8,
            foodName = "Garlic",
            foodDescription = "Whole",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 9,
            foodName = "Bread",
            foodDescription = "Sourdough",
            foodCategory = FoodCategory.BAKEDGOOD
        ),
        FoodItemEntity(
            id = 10,
            foodName = "Bread",
            foodDescription = "Whole Wheat",
            foodCategory = FoodCategory.BAKEDGOOD
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
            foodName = "Kale",
            foodDescription = "Russian",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 14,
            foodName = "Cheese",
            foodDescription = "Parmesan",
            foodCategory = FoodCategory.DAIRY
        ),
        FoodItemEntity(
            id = 15,
            foodName = "Onions",
            foodDescription = "Yellow",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 16,
            foodName = "Onions",
            foodDescription = "Green",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 17,
            foodName = "Onions",
            foodDescription = "White",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 18,
            foodName = "Onions",
            foodDescription = "Red",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 19,
            foodName = "Onions",
            foodDescription = "Sweet",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 20,
            foodName = "Onions",
            foodDescription = "Pearl",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 21,
            foodName = "Potatoes",
            foodDescription = "Russet",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 22,
            foodName = "Potatoes",
            foodDescription = "Gold",
            foodCategory = FoodCategory.VEGETABLE
        ),
        FoodItemEntity(
            id = 23,
            foodName = "Potatoes",
            foodDescription = "New",
            foodCategory = FoodCategory.VEGETABLE
        )
    )

    val shoppingLists = listOf(
        ShoppingListEntity(1, "Weekly Groceries", System.currentTimeMillis()),
        ShoppingListEntity(2, "Goodbye Natalie...", System.currentTimeMillis()),
    )
}