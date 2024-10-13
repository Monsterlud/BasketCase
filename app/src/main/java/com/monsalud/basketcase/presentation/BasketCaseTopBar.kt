package com.monsalud.basketcase.presentation

import android.widget.Toast
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircleOutline
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import com.monsalud.basketcase.presentation.components.InstructionsDialog
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
    val context = LocalContext.current
    val currentRoute by currentRoute.collectAsState()
    var showInstructionsDialog by remember { mutableStateOf(false) }

    val text = when (currentRoute.toRoute()) {
        "main_screen" -> "BasketCase | Lists"
        "grocery_basket_screen" -> "BasketCase | Basket"
        "pantry_items_screen" -> "BasketCase | Pantry"
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
        actions = {
            IconButton(onClick = { showInstructionsDialog = true }) {
                Icon(
                    imageVector = Icons.Filled.Info,
                    contentDescription = "Open Instructions Dialog"
                )
            }
            IconButton(onClick = { Toast.makeText(context, "Coming Soon", Toast.LENGTH_SHORT).show() }) {
                Icon(
                    imageVector = Icons.Filled.AddCircleOutline,
                    contentDescription = "Add Item to Basket"
                )
            }

        },

        modifier = modifier
    )
    if (showInstructionsDialog) {
        InstructionsDialog(
            onDismiss = { showInstructionsDialog = false },
        )
    }
}