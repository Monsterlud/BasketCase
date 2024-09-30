package com.monsalud.basketcase.presentation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.monsalud.basketcase.R
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
    val fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
    val currentRoute by currentRoute.collectAsState()

    val text = when (currentRoute.toRoute()) {
        "main_screen" -> "BasketCase | Shopping Lists"
        "grocery_basket_screen" -> "BasketCase | Grocery Basket"
        "food_items_screen" -> "BasketCase | Food Items"
        "markets_screen" -> "BasketCase | Markets"
        else -> "BasketCase"
    }

    TopAppBar(
        title = { Text(text = text, fontFamily = fontFamily) },
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