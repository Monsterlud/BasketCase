package com.monsalud.basketcase.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.zIndex
import com.monsalud.basketcase.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstructionsDialog(
    onDismiss: () -> Unit
) {
    val titles = listOf(
        "Welcome to BasketCase!",
        "Pantry:",
        "Markets:",
        "Shopping Lists:",
        "Basket:",
        "Pro Tip:"
    )
    val items = listOf(
        "BasketCase is a simple, yet powerful app that allows you to create and manage your shopping lists. \n\nSwipe for some easy instructions to get you started!",
        "Start with your Pantry, which is an inventory of items that you might want to purchase in the future. \n\nWe've pre-loaded some items for you but feel free to add, remove, or edit this list to make it your own.",
        "Next set up your Markets. \n\nThese can be grocery stores, butcher shops, bakeries, farmers markets, or any place that you will want to shop.",
        "Finally, create and manage your Shopping Lists for different needs. \n\nThink 'Weekly Groceries' or 'Italian Dinner Party with Friends'. These lists help you stay organized.",
        "This is where the magic happens! \n\nWhen you add items to a Shopping List, you are adding them to your Basket. Items in your Basket can be assigned to a specific Market. \n\nUse your Basket while shopping. Filter by Shopping List and/or Market to see exactly what you need, where you need it. No more forgotten items!",
        "Your Basket contains items from all your Shopping Lists. It's the central hub for your shopping, making it easy to stay organized! \n\n(You can revisit these instructions at any time by clicking the 'Information' button on the top right of the screen)"
    )
    val pagerState = rememberPagerState(pageCount = { items.size })
    val backgroundImage = painterResource(id = R.drawable.instructions_image)

    val tintColor = if (isSystemInDarkTheme()) {
        Color.Black.copy(alpha = 0.7f)
    } else {
        Color.White.copy(alpha = 0.7f)
    }

    BasicAlertDialog(
        onDismissRequest = onDismiss,
    ) {
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentHeight()
                .heightIn(max = 600.dp),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .paint(
                        painter = backgroundImage,
                        contentScale = ContentScale.Crop,
                        colorFilter = ColorFilter.tint(
                            color = tintColor,
                            blendMode = BlendMode.SrcOver
                        )
                    ),
            ) {
                IconButton(
                    onClick = onDismiss,
                    modifier = Modifier
                        .align(Alignment.TopEnd)
                        .padding(8.dp)
                        .zIndex(1f)
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = "Close"
                    )
                }
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.TopStart),
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
                                    .padding(8.dp)
                                    .align(Alignment.Start)
                            )
                            Text(
                                text = items[page],
                                style = MaterialTheme.typography.titleMedium,
                                modifier = Modifier
                                    .padding(8.dp)
                                    .align(Alignment.CenterHorizontally)
                            )
                        }
                    }
                    Row(
                        Modifier
                            .height(50.dp)
                            .fillMaxWidth()
                            .align(Alignment.CenterHorizontally),
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
}
