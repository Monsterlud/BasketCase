package com.monsalud.basketcase.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckBox
import androidx.compose.material.icons.outlined.CheckBoxOutlineBlank
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.monsalud.basketcase.data.localdatasource.DefaultData.markets
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ItemToPurchaseChecklist(
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    name: String,
    markets: List<MarketEntity>,
    selectedMarket: MarketEntity?,
    onMarketSelected: (MarketEntity) -> Unit,
    modifier: Modifier = Modifier,
) {
    var expanded by remember { mutableStateOf(false) }
    Row(
        modifier = modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        IconButton(
            onClick = { onCheckedChange(!isChecked) },
            modifier = modifier,
        ) {
            Icon(
                imageVector = if (isChecked) Icons.Filled.CheckBox else Icons.Outlined.CheckBoxOutlineBlank,
                contentDescription = "Checkbox",
            )
        }
        Text(
            text = name,
            modifier = modifier.weight(0.4f),
        )
        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = { expanded = !expanded },
            modifier = Modifier.weight(0.4f),
        ) {
            TextField(
                readOnly = true,
                value = selectedMarket?.marketName ?: "Select Market",
                onValueChange = {},
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                colors = ExposedDropdownMenuDefaults.textFieldColors(),
                modifier = Modifier.menuAnchor(),
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false },
            ) {
                markets.forEach { market ->
                    DropdownMenuItem(
                        text = { Text(market.marketName) },
                        onClick = {
                            onMarketSelected(market)
                            expanded = false
                        },
                    )
                }
            }
        }
    }
}

@Composable
@Preview(showBackground = true)
fun ItemToPurchaseChecklistPreview() {
    ItemToPurchaseChecklist(
        isChecked = true,
        onCheckedChange = {},
        name = "Bananas, Red",
        markets = markets,
        selectedMarket = null,
        onMarketSelected = {},
    )
}
