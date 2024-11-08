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
import androidx.compose.foundation.layout.width
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monsalud.basketcase.data.localdatasource.room.ItemToPurchaseEntity
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.domain.model.AmountType
import com.monsalud.basketcase.domain.model.PantryCategory
import com.monsalud.basketcase.ui.theme.spacing

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddItemToPurchaseBottomSheetContent(
    itemToPurchase: ItemToPurchaseEntity? = null,
    onItemToPurchaseAdded: (PantryItemEntity) -> Unit,
) {
    var selectedPantryItem by remember(itemToPurchase) {
        mutableStateOf(itemToPurchase?.pantryItemId)
    }
    var selectedMarket by remember(itemToPurchase) {
        mutableStateOf(itemToPurchase?.marketId)
    }

    var amountToPurchase by remember { mutableStateOf("0.0") }
    var expanded by remember { mutableStateOf(false) }

    Column(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(MaterialTheme.spacing.medium),
    ) {
        Text(
            text = if (itemToPurchase == null) "Add Item to your Basket" else "Update Item in your Basket",
            style = MaterialTheme.typography.headlineSmall,
        )
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.medium))

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            Box(
                modifier =
                    Modifier
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .menuAnchor(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Item to Purchase",
                        modifier =
                            Modifier
                                .weight(1f),
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        modifier = Modifier.rotate(if (expanded) 180f else 0f),
                    )
                }
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                PantryCategory.entries.forEach { pantryItemCategory ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPantryItem = 1
                            expanded = false
                        },
                        text = { Text(pantryItemCategory.getPantryCategoryName()) },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
        ) {
            Box(
                modifier =
                    Modifier
                        .fillMaxWidth()
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colorScheme.outline,
                            shape = RoundedCornerShape(4.dp),
                        )
                        .background(MaterialTheme.colorScheme.surface)
                        .padding(16.dp)
                        .menuAnchor(),
            ) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    verticalAlignment = Alignment.CenterVertically,
                ) {
                    Text(
                        text = "Market Name",
                        modifier =
                            Modifier
                                .weight(1f),
                    )
                    Icon(
                        imageVector = Icons.Filled.ArrowDropDown,
                        contentDescription = "Dropdown arrow",
                        modifier = Modifier.rotate(if (expanded) 180f else 0f),
                    )
                }
            }
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                PantryCategory.entries.forEach { pantryCategory ->
                    DropdownMenuItem(
                        onClick = {
                            selectedPantryItem = 1
                            expanded = false
                        },
                        text = { Text(pantryCategory.getPantryCategoryName()) },
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            OutlinedTextField(
                value = amountToPurchase,
                onValueChange = { amountToPurchase = it },
                label = { Text("Amount to Purchase") },
                modifier = Modifier.weight(1f),
            )
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            ExposedDropdownMenuBox(
                expanded = expanded,
                onExpandedChange = { expanded = !expanded },
                modifier = Modifier.weight(2f),
            ) {
                Box(
                    modifier =
                        Modifier
                            .fillMaxWidth()
                            .border(
                                width = 1.dp,
                                color = MaterialTheme.colorScheme.outline,
                                shape = RoundedCornerShape(4.dp),
                            )
                            .background(MaterialTheme.colorScheme.surface)
                            .padding(16.dp)
                            .menuAnchor(),
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        verticalAlignment = Alignment.CenterVertically,
                    ) {
                        Text(
                            text = "Amount Type",
                            modifier = Modifier,
                        )
                        Icon(
                            imageVector = Icons.Filled.ArrowDropDown,
                            contentDescription = "Dropdown arrow",
                            modifier = Modifier.rotate(if (expanded) 180f else 0f),
                        )
                    }
                }
                ExposedDropdownMenu(
                    expanded = expanded,
                    onDismissRequest = { expanded = false },
                ) {
                    PantryCategory.entries.forEach { pantryCategory ->
                        DropdownMenuItem(
                            onClick = {
                                selectedPantryItem = 1
                                expanded = false
                            },
                            text = { Text(pantryCategory.getPantryCategoryName()) },
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(MaterialTheme.spacing.extraLarge))
        Row(
            modifier = Modifier.fillMaxWidth(),
        ) {
            Button(
                onClick = {},
                modifier = Modifier.width(180.dp),
            ) {
                Text(text = "Add New Pantry Item")
            }
            Spacer(modifier = Modifier.width(MaterialTheme.spacing.medium))
            Button(
                onClick = {},
                modifier = Modifier.width(180.dp),
            ) {
                Text(text = if (itemToPurchase == null) "Add to Basket" else "Update Basket")
            }
        }
    }
}

@Preview
@Composable
fun AddItemToPurchaseBottomSheetContentPreview() {
    AddItemToPurchaseBottomSheetContent(
        itemToPurchase =
            ItemToPurchaseEntity(
                id = 10000,
                pantryItemId = 1,
                marketId = 1,
                amountToPurchase = 1.0,
                amountType = AmountType.LB,
                itemNotes = "only need 1 lb",
            ),
        onItemToPurchaseAdded = {},
    )
}
