package com.monsalud.basketcase.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monsalud.basketcase.ui.theme.spacing

@Composable
fun GroceryListScreen(
    modifier: Modifier = Modifier
) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Shopping Cart Screen with filtering options",
            style = MaterialTheme.typography.bodyLarge,
            modifier = modifier
                .align(Alignment.TopStart)
                .padding(16.dp),
        )
        FloatingActionButton(
            onClick = { /* Handle FAB click */ },
            modifier = Modifier.align(Alignment.BottomEnd).padding(MaterialTheme.spacing.medium)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
}

@Composable
@Preview
fun ShoppingListScreenPreview() {
    GroceryListScreen()
}