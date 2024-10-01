package com.monsalud.basketcase.presentation.screens

import android.util.Log
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monsalud.basketcase.R
import com.monsalud.basketcase.data.localdatasource.DummyData.markets
import com.monsalud.basketcase.data.localdatasource.room.MarketEntity
import com.monsalud.basketcase.ui.theme.spacing
import timber.log.Timber

@Composable
fun MarketsScreen(
    markets: List<MarketEntity>,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "add or edit your favorite markets here",
                fontFamily = FontFamily(Font(R.font.playwriteitmoderna_extralight)),
                modifier = modifier
                    .padding(16.dp),
            )
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(8.dp)
            ) {
                items(markets) { market ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .clip(RoundedCornerShape(8.dp))
                            .clickable { /* Handle item click */ }
                    ) {
                        // Market Type block with colored background
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.secondaryContainer)
                                .padding(8.dp)
                        ) {
                            Text(
                                text = market.marketType.getMarketTypeName(),
                                fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                                fontSize = 12.sp,
                                color = MaterialTheme.colorScheme.onSecondaryContainer,
                                modifier = Modifier.align(Alignment.CenterEnd)
                            )
                        }
                        // Market Name and Address block
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .background(MaterialTheme.colorScheme.surface)
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
                                    color = MaterialTheme.colorScheme.onSurface,
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
                }
            }
        }

        FloatingActionButton(
            onClick = { /* Handle FAB click */ },
            modifier = Modifier
                .align(Alignment.BottomEnd)
                .padding(MaterialTheme.spacing.extraLarge)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
@Preview
fun MarketsScreenPreview() {
    MarketsScreen(markets = markets)
}