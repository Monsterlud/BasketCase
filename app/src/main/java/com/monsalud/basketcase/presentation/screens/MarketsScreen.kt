package com.monsalud.basketcase.presentation.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.DismissValue
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
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
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
import androidx.lifecycle.viewmodel.compose.viewModel
import com.monsalud.basketcase.R
import com.monsalud.basketcase.data.localdatasource.DefaultData.markets
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.AddMarketBottomSheetContent
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketsScreen(
    viewModel: BasketCaseViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onMarketClick: () -> Unit = {},
) {
    val markets by viewModel.markets.collectAsState(initial = emptyList())
    val sortedMarkets = remember(markets) {
        markets.sortedBy { it.marketName.lowercase() }
    }
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "add or edit your favorite places to shop",
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
                    items = sortedMarkets,
                    key = { it.id }
                ) { market ->
                    val dismissState = rememberSwipeToDismissBoxState(
                        confirmValueChange = { dismissValue ->
                            if (dismissValue == SwipeToDismissBoxValue.EndToStart) {
                                viewModel.deleteMarketFromDatabase(market)
                                true
                            } else {
                                false
                            }
                        }
                    )
                    SwipeToDismissBox(
                        state = dismissState,
                        backgroundContent = {
                            val color by animateColorAsState(
                                when (dismissState.targetValue) {
                                    SwipeToDismissBoxValue.Settled -> MaterialTheme.colorScheme.surface
                                    else -> Color.Red
                                }, label = "backgroundColorAnimation"
                            )
                            Box(
                                Modifier
                                    .fillMaxSize()
                                    .padding(8.dp)
                                    .background(color)
                                    .padding(horizontal = 20.dp),
                                contentAlignment = Alignment.CenterEnd
                            ) {
                                Icon(
                                    Icons.Default.Delete,
                                    contentDescription = "Delete",
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
                                // Market Type block with colored background
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.primary)
                                        .padding(8.dp)
                                ) {
                                    Text(
                                        text = market.marketType.getMarketTypeName(),
                                        fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                        color = MaterialTheme.colorScheme.onPrimary,
                                        modifier = Modifier.align(Alignment.CenterEnd)
                                    )
                                }
                                // Market Name and Address block
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .background(MaterialTheme.colorScheme.secondaryContainer)
                                        .padding(0.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .padding(vertical = 0.dp, horizontal = 0.dp)
                                    ) {
                                        Text(
                                            text = market.marketName,
                                            fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                            fontSize = 14.sp,
                                            color = MaterialTheme.colorScheme.onSecondaryContainer,
                                            modifier = Modifier
                                                .padding(start = 8.dp, top = 8.dp, bottom = 4.dp),
                                        )
                                        market.marketAddress?.let {
                                            Text(
                                                text = it,
                                                fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                                fontSize = 12.sp,
                                                modifier = Modifier
                                                    .padding(start = 8.dp, bottom = 8.dp),
                                            )
                                        }
                                    }
                                }
                            }

                        },
                        enableDismissFromEndToStart = true,
                        enableDismissFromStartToEnd = false,
                    )
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
                AddMarketBottomSheetContent(onMarketAdded = {
                    viewModel.upsertMarketToDatabase(it)
                    isBottomSheetOpen = false
                })
            }
        }
    }
}




@Composable
@Preview
fun MarketsScreenPreview() {
    MarketsScreen(onMarketClick = {})
}