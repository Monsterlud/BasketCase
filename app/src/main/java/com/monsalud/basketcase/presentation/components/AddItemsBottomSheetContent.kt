package com.monsalud.basketcase.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.unit.dp
import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.domain.model.FoodCategory
import com.monsalud.basketcase.ui.theme.spacing
import timber.log.Timber

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemsBottomSheetContent(
    foodItem: FoodItemEntity? = null,
    onFoodItemAdded: (FoodItemEntity) -> Unit,
) {
    var selectedFoodCategory by remember(foodItem) {
        mutableStateOf(foodItem?.foodCategory ?: FoodCategory.MISCELLANEOUS)
    }
    var foodItemName by remember(foodItem) { mutableStateOf(foodItem?.foodName ?: "") }
    var foodItemDescription by remember(foodItem) {
        mutableStateOf(
            foodItem?.foodDescription ?: ""
        )
    }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(MaterialTheme.spacing.medium)
    ) {
        Text(
            text = if (foodItem == null) "Add Food Item" else "Update Food Item",
            style = MaterialTheme.typography.headlineSmall
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        OutlinedTextField(
            value = foodItemName,
            onValueChange = { foodItemName = it },
            label = { Text("Food Item Name (e.g. \"Onion\")") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        OutlinedTextField(
            value = foodItemDescription,
            onValueChange = { foodItemDescription = it },
            label = { Text("Food Item Description (e.g. \"Yellow\")") },
            modifier = Modifier.fillMaxWidth()
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded }
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(
                        width = 1.dp,
                        color = MaterialTheme.colorScheme.outline,
                        shape = RoundedCornerShape(4.dp)
                    )
                    .background(MaterialTheme.colorScheme.surface)
                    .padding(16.dp)
                    .menuAnchor()
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = selectedFoodCategory.getFoodCategoryName(),
                        modifier = Modifier
                            .weight(1f)
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        modifier = Modifier.rotate(if (expanded) 180f else 0f)
                    )
                }
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                FoodCategory.entries.forEach { foodCategory ->
                    DropdownMenuItem(
                        onClick = {
                            selectedFoodCategory = foodCategory
                            expanded = false
                        },
                        text = { Text(foodCategory.getFoodCategoryName()) }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        Button(
            onClick = {
                Timber.d("Add/Update Food Item Button Clicked")
                var newFoodItem: FoodItemEntity? = null
                newFoodItem = foodItem?.copy(
                    foodName = foodItemName,
                    foodDescription = foodItemDescription,
                    foodCategory = selectedFoodCategory,
                )
                    ?: FoodItemEntity(
                        foodName = foodItemName,
                        foodDescription = foodItemDescription,
                        foodCategory = selectedFoodCategory,
                    )
                onFoodItemAdded(newFoodItem)
            },
            modifier = Modifier.align(Alignment.End),
            enabled = foodItemName.isNotBlank()
        ) {
            Text(if (foodItem == null) "Add Food Item" else "Update Food Item")
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
    }
}