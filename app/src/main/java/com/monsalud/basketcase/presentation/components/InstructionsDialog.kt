package com.monsalud.basketcase.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstructionsDialog(
    onDismiss: () -> Unit
) {
    val titles = listOf(
        "Welcome to BasketCase!",
        "Pantry Essentials:",
        "Markets:",
        "Shopping Lists:",
        "Basket:"
    )
    val items = listOf(
        "BasketCase is a simple, yet powerful app that allows you to create and manage your shopping lists. Here are some easy instructions to get started.",
        "Add, remove, or edit items that you might want to purchase in the future. Think of this as your master inventory of food items.",
        "Add, remove, or edit your favorite places to shop.",
        "Create and manage your shopping lists. As an example, you can have one weekly grocery list and another list for a special dinner party.",
        "This is your all-in-one shopping list that you can filter by market or by list. When you are at a specific market to shop for that dinner party, just filter the list to that market and that list. Your basket is now focused on only what you need to buy!"
    )
    val pagerState = rememberPagerState(pageCount = { items.size })

    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp)
                    .heightIn(max = 500.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                HorizontalPager(
                    state = pagerState,
                    modifier = Modifier.weight(1f)
                ) { page ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(16.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {

                        Text(
                            text = titles[page],
                            style = MaterialTheme.typography.titleLarge,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.Start)
                        )
                        Spacer(modifier = Modifier.height(16.dp))
                        Text(
                            text = items[page],
                            style = MaterialTheme.typography.titleMedium,
                            modifier = Modifier
                                .padding(16.dp)
                                .align(Alignment.CenterHorizontally)
                        )
                    }
                }
                Row(
                    Modifier
                        .height(50.dp)
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.Bottom
                ) {
                    repeat(pagerState.pageCount) { iteration ->
                        val color =
                            if (pagerState.currentPage == iteration) Color.DarkGray else Color.LightGray
                        Box(
                            modifier = Modifier
                                .padding(8.dp)
                                .background(color, CircleShape)
                                .size(10.dp)
                        )
                    }
                }
            }
        }
    }
}
