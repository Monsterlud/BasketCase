package com.monsalud.basketcase.presentation.screens

import androidx.compose.foundation.Image
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
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.monsalud.basketcase.R
import com.monsalud.basketcase.data.localdatasource.DefaultData
import com.monsalud.basketcase.data.localdatasource.room.ShoppingListEntity
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.AddListBottomSheetContent
import com.monsalud.basketcase.presentation.components.InstructionsDialog
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    shoppingLists: List<ShoppingListEntity> = DefaultData.shoppingLists,
) {
    val viewModel: BasketCaseViewModel = koinViewModel()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    val userPreferences by viewModel.userPreferencesFlow.collectAsStateWithLifecycle()
    val isLoadingPreferences by viewModel.isLoadingPreferences.collectAsStateWithLifecycle()

    val showOnboardingDialog by remember {
        derivedStateOf {
            !isLoadingPreferences && !userPreferences.hasSeenOnboardingInstructions
        }
    }
    val showShoppingListInstructions by remember {
        derivedStateOf {
            !isLoadingPreferences && !userPreferences.hasSeenShoppingListInstructions
        }
    }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            if (showShoppingListInstructions) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .background(MaterialTheme.colorScheme.secondaryContainer)
                        .padding(8.dp)
                        .clip(RoundedCornerShape(8.dp))
                ) {
                    Text(
                        text = "add as many different shopping lists as you like",
                        color = MaterialTheme.colorScheme.onSecondaryContainer,
                        modifier = Modifier.padding(start = 24.dp, top = 12.dp, bottom = 12.dp, end = 64.dp),
                    )
                    IconButton(
                        onClick = { viewModel.updateHasSeenShoppingListInstructions(true) },
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
                items(shoppingLists) { shoppingList ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { /* Handle item click */ }
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondary)
                                .padding(8.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .align(Alignment.CenterStart)
                            ) {
                                Image(
                                    imageVector = Icons.Filled.List,
                                    contentDescription = "List Icon",
                                    colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
                                    modifier = Modifier
                                        .padding(8.dp)
                                )
                                Text(
                                    text = shoppingList.listName,
                                    fontSize = 16.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = MaterialTheme.colorScheme.onSecondary,
                                    modifier = Modifier
                                        .padding(8.dp),
                                )
                            }
                            Image(
                                painter = painterResource(id = R.drawable.groceries),
                                contentDescription = "Groceries Icon",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.onSecondary),
                                modifier = Modifier
                                    .align(Alignment.CenterEnd)
                                    .padding(8.dp)
                            )
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
    }
    if (isBottomSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = false },
            sheetState = rememberModalBottomSheetState(),
        ) {
            AddListBottomSheetContent()
        }
    }
    if (showOnboardingDialog) {
        InstructionsDialog(
            onDismiss = {
                viewModel.updateHasSeenOnboardingInstructions(true)
            }
        )
    }
}


@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}