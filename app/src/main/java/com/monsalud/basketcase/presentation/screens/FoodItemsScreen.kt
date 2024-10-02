package com.monsalud.basketcase.presentation.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monsalud.basketcase.R
import com.monsalud.basketcase.data.localdatasource.DefaultData.foodItems
import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.AddItemsBottomSheetContent
import com.monsalud.basketcase.presentation.components.AddListBottomSheetContent
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemsScreen(
    viewModel: BasketCaseViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onAddFoodItemClick: () -> Unit = {},
) {
    val foodItems by viewModel.foodItems.collectAsState(initial = emptyList())
    val sortedFoodItems = remember(foodItems) {
        foodItems.sortedWith(
            compareBy<FoodItemEntity> { it.foodCategory }.thenBy { it.foodName.lowercase() }
        )
    }
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "add or edit items here that you might want to purchase in the future",
                fontFamily = FontFamily(Font(R.font.playwriteitmoderna_extralight)),
                modifier = modifier
                    .padding(16.dp),
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(sortedFoodItems) { foodItem ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { /* Handle item click */ }
                    ) {
                        // Food Item Type block with colored background
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.primary)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = foodItem.foodCategory.getFoodCategoryName(),
                                fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                color = MaterialTheme.colorScheme.onPrimary,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                        // Food Item Name block
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(0.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp)
                                    .clip(RoundedCornerShape(8.dp)) // Clip content to rounded corners
                            ) {
                                Text(
                                    text = foodItem.foodName + if (foodItem.foodDescription != null) ", " + foodItem.foodDescription else "",
                                    fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                    fontSize = 14.sp,
                                    color = MaterialTheme.colorScheme.onSecondaryContainer,
                                    modifier = Modifier
                                        .padding(8.dp),
                                )
                            }
                        }
                    }
                }
            }
        }

        FloatingActionButton(
            onClick = { isBottomSheetOpen = true },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MaterialTheme.spacing.extraLarge)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }

        if (isBottomSheetOpen) {
            ModalBottomSheet(
                onDismissRequest = { isBottomSheetOpen = false },
                sheetState = rememberModalBottomSheetState(),
            ) {
                AddItemsBottomSheetContent(onFoodItemAdded = {
                    viewModel.addFoodItemToDatabase(it)
                    isBottomSheetOpen = false
                })
            }
        }
    }
}


@Composable
@Preview
fun FoodItemsScreenPreview() {
    FoodItemsScreen(onAddFoodItemClick = {})
}