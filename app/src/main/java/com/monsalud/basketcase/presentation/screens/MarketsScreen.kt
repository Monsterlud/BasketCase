package com.monsalud.basketcase.presentation.screens

//import com.monsalud.basketcase.ui.theme.Colors.DeleteRed
//import com.monsalud.basketcase.ui.theme.Colors.EditGreen
import android.widget.Toast
import androidx.compose.foundation.background
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
import androidx.compose.material3.surfaceColorAtElevation
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
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
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.AddMarketBottomSheetContent
import com.monsalud.basketcase.ui.theme.deleteRed
import com.monsalud.basketcase.ui.theme.editGreen
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarketsScreen(
    viewModel: BasketCaseViewModel = koinViewModel(),
    modifier: Modifier = Modifier,
    onMarketClick: () -> Unit = {},
) {
    val context = LocalContext.current

    val markets by viewModel.markets.collectAsState(initial = emptyList())
    val sortedMarkets = remember(markets) {
        markets.sortedBy { it.marketName.lowercase() }
    }
    var marketToEdit by remember { mutableStateOf<MarketEntity?>(null) }
    var isBottomSheetOpen by remember { mutableStateOf(false) }
    var editActionCounter by remember { mutableStateOf(0) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "add or edit your favorite places to shop",
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
                    key(market.id, editActionCounter) {
                        val dismissState = rememberSwipeToDismissBoxState(
                            confirmValueChange = { dismissValue ->
                                when (dismissValue) {
                                    SwipeToDismissBoxValue.EndToStart -> {
                                        viewModel.deleteMarketFromDatabase(market)
                                        Toast.makeText(context, "Market deleted!", Toast.LENGTH_SHORT).show()
                                        true
                                    }

                                    SwipeToDismissBoxValue.StartToEnd -> {
                                        marketToEdit = market
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
                                    SwipeToDismissBoxValue.Settled -> {
                                        val elevatedColor = MaterialTheme.colorScheme.surfaceColorAtElevation(5.dp)
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
                                    // Market Type block with colored background
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .background(MaterialTheme.colorScheme.secondary)
                                            .padding(8.dp)
                                    ) {
                                        Text(
                                            text = market.marketType.getMarketTypeName(),
                                            color = MaterialTheme.colorScheme.onSecondary,
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
                                                fontSize = 14.sp,
                                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                                modifier = Modifier
                                                    .padding(
                                                        start = 8.dp,
                                                        top = 8.dp,
                                                        bottom = 4.dp
                                                    ),
                                            )
                                            market.marketAddress?.let {
                                                Text(
                                                    text = it,
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
                skipPartiallyExpanded = true
            )
            ModalBottomSheet(
                onDismissRequest = {
                    isBottomSheetOpen = false
                    marketToEdit = null
                },
                sheetState = modalBottomSheetState,
            ) {
                AddMarketBottomSheetContent(
                    market = marketToEdit,
                    onMarketAdded = {
                        viewModel.upsertMarketToDatabase(it)
                        isBottomSheetOpen = false
                        Toast.makeText(context, if (marketToEdit == null) "Market added!" else "Market updated!", Toast.LENGTH_SHORT).show()
                        marketToEdit = null
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