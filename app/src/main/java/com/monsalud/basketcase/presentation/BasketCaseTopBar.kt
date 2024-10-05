package com.monsalud.basketcase.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import com.monsalud.basketcase.presentation.navigation.Screen
import com.monsalud.basketcase.presentation.navigation.toRoute
import kotlinx.coroutines.flow.StateFlow

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketCaseTopBar(
    currentRoute: StateFlow<Screen>,
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val currentRoute by currentRoute.collectAsState()

    val text = when (currentRoute.toRoute()) {
        "main_screen" -> "BasketCase | Lists"
        "grocery_basket_screen" -> "BasketCase | Basket"
        "food_items_screen" -> "BasketCase | Items"
        "markets_screen" -> "BasketCase | Markets"
        else -> "BasketCase"
    }

    TopAppBar(
        title = { Text(text = text) },
        navigationIcon = {
            IconButton(onClick = onMenuClick) {
                Icon(
                    Icons.Default.Menu, contentDescription = "Hamburger Menu"
                )
            }
        },

        modifier = modifier
    )
}