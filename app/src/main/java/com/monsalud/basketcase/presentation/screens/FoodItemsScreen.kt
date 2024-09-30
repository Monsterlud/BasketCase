package com.monsalud.basketcase.presentation.screens

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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.monsalud.basketcase.R
import com.monsalud.basketcase.data.localdatasource.DummyData.foodItems
import com.monsalud.basketcase.data.localdatasource.room.FoodItemEntity
import com.monsalud.basketcase.ui.theme.spacing

@Composable
fun FoodItemsScreen(
    foodItems: List<FoodItemEntity>,
    modifier: Modifier = Modifier,
    onAddFoodItemClick: () -> Unit,
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier.fillMaxSize()
        ) {
            Text(
                text = "add or edit items here that you might want to add to your grocery basket",
                fontFamily = FontFamily(Font(R.font.playwriteitmoderna_extralight)),
                modifier = modifier
                    .padding(16.dp),
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
            ) {
                items(foodItems) { foodItem ->
                    // Replace Card with Row or Column and apply styling
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)
                            .background(
                                Color.White,
                                shape = RoundedCornerShape(8.dp)
                            ) // Simulate card background
                            .clip(RoundedCornerShape(8.dp)) // Clip content to rounded corners
                            .clickable { /* Handle item click */ } // Make item clickable
                    ) {
                        Text(
                            text = foodItem.foodName + if (foodItem.foodDescription != null) ", " + foodItem.foodDescription else "",
                            fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                            fontSize = 14.sp,
                            modifier = Modifier
                                .padding(16.dp),
                        )
                        Spacer(modifier = Modifier.weight(1f))
                        Text(
                            text = foodItem.foodCategory.getFoodCategoryName(),
                            fontFamily = FontFamily(Font(R.font.loravariablefont_wght)),
                            fontSize = 12.sp,
                            modifier = Modifier.padding(16.dp),
                        )
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
fun FoodItemsScreenPreview() {
    FoodItemsScreen(foodItems, onAddFoodItemClick = {})
}