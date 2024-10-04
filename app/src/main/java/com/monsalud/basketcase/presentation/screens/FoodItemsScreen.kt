package com.monsalud.basketcase.presentation.screens

import androidx.compose.foundation.background
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
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monsalud.basketcase.R
import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.AddItemsBottomSheetContent
import com.monsalud.basketcase.ui.theme.Colors.EditGreen
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FoodItemsScreen(
    viewModel: BasketCaseViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onAddFoodItemClick: () -> Unit = {},
) {
    val foodItems by viewModel.foodItems.collectAsStateWithLifecycle()
    val sortedFoodItems = remember(foodItems) {
        foodItems.sortedWith(
            compareBy<FoodItemEntity> { it.foodCategory }.thenBy { it.foodName.lowercase() }
        )
    }
    var foodItemToEdit by remember { mutableStateOf<FoodItemEntity?>(null) }
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var editActionCounter by remember { mutableStateOf(0) }

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
                items(
                    items = sortedFoodItems,
                    key = { it.id }
                ) { foodItem ->
                    key(foodItem.id, editActionCounter) {
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { dismissValue ->
                                when (dismissValue) {
                                    SwipeToDismissBoxValue.EndToStart -> {
                                        viewModel.deleteFoodItemFromDatabase(foodItem)
                                        true
                                    }

                                    SwipeToDismissBoxValue.StartToEnd -> {
                                        foodItemToEdit = foodItem
                                        isBottomSheetOpen = true
                                        editActionCounter++
                                        true
                                    }

                                    else -> false
                                }
                            }
                        )

                        SwipeToDismissBox(
                            state = dismissState,
                            backgroundContent = {
                                val (color, icon) = when (dismissState.targetValue) {
                                    SwipeToDismissBoxValue.EndToStart -> Color.Red to Icons.Default.Delete
                                    SwipeToDismissBoxValue.StartToEnd -> EditGreen to Icons.Default.Edit
                                    SwipeToDismissBoxValue.Settled -> MaterialTheme.colorScheme.surface to null
                                    else -> Pair(
                                        MaterialTheme.colorScheme.surface,
                                        Icons.Default.Delete
                                    )
                                }
                                Box(
                                    Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                        .background(color)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) Alignment.CenterStart else Alignment.CenterEnd
                                ) {
                                    Icon(
                                        if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) Icons.Default.Edit else Icons.Default.Delete,
                                        contentDescription = if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) "Edit" else "Delete",
                                        tint = Color.White
                                    )
                                }
                            },
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))
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
                                                .clip(RoundedCornerShape(8.dp))
                                        ) {
                                            Text(
                                                text = buildString {
                                                    append(foodItem.foodName)
                                                    if (!foodItem.foodDescription.isNullOrBlank()) {
                                                        append(", ")
                                                        append(foodItem.foodDescription)
                                                    }
                                                },
                                                fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                                modifier = Modifier
                                                    .padding(8.dp),
                                            )
                                        }
                                    }
                                }
                            },
                            enableDismissFromEndToStart = true,
                            enableDismissFromStartToEnd = true,
                        )

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
                onDismissRequest = {
                    isBottomSheetOpen = false
                    foodItemToEdit = null
                },
                sheetState = rememberModalBottomSheetState(),
            ) {
                AddItemsBottomSheetContent(
                    foodItem = foodItemToEdit,
                    onFoodItemAdded = {
                        viewModel.upsertFoodItemToDatabase(it)
                        isBottomSheetOpen = false
                        foodItemToEdit = null
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