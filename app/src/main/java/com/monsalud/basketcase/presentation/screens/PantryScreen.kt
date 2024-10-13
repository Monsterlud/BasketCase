package com.monsalud.basketcase.presentation.screens

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.ime
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monsalud.basketcase.data.localdatasource.room.PantryItemEntity
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.AddPantryItemBottomSheetContent
import com.monsalud.basketcase.ui.theme.deleteRed
import com.monsalud.basketcase.ui.theme.editGreen
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PantryScreen(
    viewModel: BasketCaseViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current

    val pantryItems by viewModel.pantryItems.collectAsStateWithLifecycle()

    var searchText by remember { mutableStateOf("") }
    val filteredPantryItems = remember(pantryItems, searchText) {
        pantryItems.filter { pantryItem ->
            pantryItem.pantryItemName.contains(searchText, ignoreCase = true) ||
                    pantryItem.pantryItemDescription?.contains(
                        searchText,
                        ignoreCase = true
                    ) == true ||
                    pantryItem.pantryItemCategory.name.contains(searchText, ignoreCase = true)
        }.sortedWith(
            compareBy<PantryItemEntity> { it.pantryItemCategory }.thenBy { it.pantryItemName.lowercase() }
        )
    }

    var pantryItemToEdit by remember { mutableStateOf<PantryItemEntity?>(null) }

    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var editActionCounter by remember { mutableStateOf(0) }

    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle()
    val isLoadingPreferences by viewModel.isLoadingPreferences.collectAsStateWithLifecycle()

    val showPantryInstructions by remember {
        derivedStateOf {
            !isLoadingPreferences && !userPreferences.hasSeenPantryInstructions
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            TextField(
                value = searchText,
                onValueChange = { searchText = it },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search"
                    )
                },
                colors = TextFieldDefaults.textFieldColors(
                    focusedIndicatorColor = Color.Transparent, // Hide the focused indicator
                    unfocusedIndicatorColor = Color.Transparent, // Hide the unfocused indicator
                    // ... other color customizations if needed ...
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .clip(CircleShape),
                placeholder = { Text(text = "Search pantry items") }
            )
            if (showPantryInstructions) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                ) {
                    Text(
                        text = "add or edit items here that you might want to purchase in the future. consider this your master inventory of food items. Swipe to edit or delete an item.",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = modifier
                            .padding(start = 24.dp, top = 12.dp, bottom = 12.dp, end = 64.dp),
                    )
                    IconButton(
                        onClick = { viewModel.updateHasSeenPantryInstructions(true) },
                        modifier = Modifier
                            .align(Alignment.TopEnd)
                            .padding(0.dp)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = "Close Instructions",
                            tint = MaterialTheme.colorScheme.onSecondaryContainer,
                        )
                    }
                }
            }
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(
                    items = filteredPantryItems,
                    key = { it.id },
                ) { pantryItem ->
                    key(pantryItem.id, editActionCounter) {
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { dismissValue ->
                                when (dismissValue) {
                                    SwipeToDismissBoxValue.EndToStart -> {
                                        viewModel.deletePantryItemFromDatabase(pantryItem)
                                        Toast.makeText(
                                            context,
                                            "Pantry Item deleted!",
                                            Toast.LENGTH_SHORT
                                        ).show()
                                        true
                                    }

                                    SwipeToDismissBoxValue.StartToEnd -> {
                                        pantryItemToEdit = pantryItem
                                        isBottomSheetOpen = true
                                        editActionCounter++
                                        true
                                    }

                                    else -> false
                                }
                            },
                            positionalThreshold = { distance: Float ->
                                distance * 0.28f
                            }
                        )

                        SwipeToDismissBox(
                            state = dismissState,
                            backgroundContent = {
                                val (color, icon) = when (dismissState.targetValue) {
                                    SwipeToDismissBoxValue.Settled -> {
                                        val elevatedColor =
                                            MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp)
                                        elevatedColor to null
                                    }

                                    SwipeToDismissBoxValue.EndToStart -> deleteRed to Icons.Default.Delete
                                    SwipeToDismissBoxValue.StartToEnd -> editGreen to Icons.Default.Edit
                                    else -> MaterialTheme.colorScheme.background to null
                                }
                                Box(
                                    Modifier
                                        .fillMaxSize()
                                        .padding(8.dp)
                                        .background(color)
                                        .padding(horizontal = 20.dp),
                                    contentAlignment = if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) Alignment.CenterStart else Alignment.CenterEnd
                                ) {
                                    icon?.let {
                                        Icon(
                                            imageVector = icon,
                                            contentDescription = if (dismissState.targetValue == SwipeToDismissBoxValue.StartToEnd) "Edit" else "Delete",
                                            tint = Color.White
                                        )
                                    }
                                }
                            },
                            content = {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(8.dp)
                                        .clip(RoundedCornerShape(8.dp))
                                ) {
                                    // Pantry Item Type block with colored background
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(MaterialTheme.colorScheme.secondary)
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = pantryItem.pantryItemCategory.getPantryCategoryName(),
                                            color = MaterialTheme.colorScheme.onSecondary,
                                            modifier = Modifier.align(Alignment.CenterEnd)
                                        )
                                    }
                                    // Pantry Item Name block
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
                                                    append(pantryItem.pantryItemName)
                                                    if (!pantryItem.pantryItemDescription.isNullOrBlank()) {
                                                        append(", ")
                                                        append(pantryItem.pantryItemDescription)
                                                    }
                                                },
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
            val modalBottomSheetState = rememberModalBottomSheetState(
                skipPartiallyExpanded = true,
            )
            ModalBottomSheet(
                onDismissRequest = {
                    isBottomSheetOpen = false
                    pantryItemToEdit = null
                },
                sheetState = modalBottomSheetState,
                windowInsets = WindowInsets.ime
            ) {
                AddPantryItemBottomSheetContent(
                    pantryItem = pantryItemToEdit,
                    onPantryItemAdded = {
                        viewModel.upsertPantryItemToDatabase(it)
                        isBottomSheetOpen = false
                        Toast.makeText(
                            context,
                            if (pantryItemToEdit == null) "Pantry Item added!" else "Pantry Item updated!",
                            Toast.LENGTH_SHORT
                        ).show()
                        pantryItemToEdit = null
                    })
            }
        }
    }
}


@Composable
@Preview
fun PantryEssentialsScreenPreview() {
    PantryScreen()
}