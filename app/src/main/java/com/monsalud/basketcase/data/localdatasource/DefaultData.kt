package com.monsalud.basketcase.data.localdatasource

import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.domain.model.MarketType
import com.monsalud.basketcase.domain.model.PantryCategory

object DefaultData {
    // todo: remove the dummy data after setting up ui. keep only pantryItems.

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

    val pantryItems = listOf(
        PantryItemEntity(
            id = 1,
            pantryItemName = "Bananas",
            pantryItemCategory = PantryCategory.FRUIT
        ),
        PantryItemEntity(
            id = 2,
            pantryItemName = "Anchovies",
            pantryItemCategory = PantryCategory.PANTRY
        ),
        PantryItemEntity(
            id = 3,
            pantryItemName = "AP Flour",
            pantryItemCategory = PantryCategory.PANTRY
        ),
        PantryItemEntity(
            id = 4,
            pantryItemName = "Salmon",
            pantryItemDescription = "Filet",
            pantryItemCategory = PantryCategory.SEAFOOD
        ),
        PantryItemEntity(
            id = 5,
            pantryItemName = "Milk",
            pantryItemDescription = "Skim",
            pantryItemCategory = PantryCategory.DAIRY
        ),
        PantryItemEntity(
            id = 6,
            pantryItemName = "Hot Sauce",
            pantryItemDescription = "Tabasco",
            pantryItemCategory = PantryCategory.PANTRY
        ),
        PantryItemEntity(
            id = 7,
            pantryItemName = "Cheese",
            pantryItemDescription = "Cheddar",
            pantryItemCategory = PantryCategory.DAIRY
        ),
        PantryItemEntity(
            id = 8,
            pantryItemName = "Garlic",
            pantryItemDescription = "Whole",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 9,
            pantryItemName = "Bread",
            pantryItemDescription = "Sourdough",
            pantryItemCategory = PantryCategory.BAKEDGOOD
        ),
        PantryItemEntity(
            id = 10,
            pantryItemName = "Bread",
            pantryItemDescription = "Whole Wheat",
            pantryItemCategory = PantryCategory.BAKEDGOOD
        ),
        PantryItemEntity(
            id = 11,
            pantryItemName = "Broccolini",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 12,
            pantryItemName = "Broccoli Rabe",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 13,
            pantryItemName = "Kale",
            pantryItemDescription = "Russian",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 14,
            pantryItemName = "Cheese",
            pantryItemDescription = "Parmesan",
            pantryItemCategory = PantryCategory.DAIRY
        ),
        PantryItemEntity(
            id = 15,
            pantryItemName = "Onions",
            pantryItemDescription = "Yellow",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 16,
            pantryItemName = "Onions",
            pantryItemDescription = "Green",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 17,
            pantryItemName = "Onions",
            pantryItemDescription = "White",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 18,
            pantryItemName = "Onions",
            pantryItemDescription = "Red",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 19,
            pantryItemName = "Onions",
            pantryItemDescription = "Sweet",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 20,
            pantryItemName = "Onions",
            pantryItemDescription = "Pearl",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 21,
            pantryItemName = "Potatoes",
            pantryItemDescription = "Russet",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 22,
            pantryItemName = "Potatoes",
            pantryItemDescription = "Gold",
            pantryItemCategory = PantryCategory.VEGETABLE
        ),
        PantryItemEntity(
            id = 23,
            pantryItemName = "Potatoes",
            pantryItemDescription = "New",
            pantryItemCategory = PantryCategory.VEGETABLE
        )
    )

    val shoppingLists = listOf(
        ShoppingListEntity(1, "Weekly Groceries", System.currentTimeMillis()),
        ShoppingListEntity(2, "Spanish Tapas Party", System.currentTimeMillis()),
        ShoppingListEntity(3, "Meatloaf", System.currentTimeMillis()),
    )
}