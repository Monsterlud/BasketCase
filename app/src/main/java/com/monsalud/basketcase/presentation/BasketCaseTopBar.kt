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
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import com.monsalud.basketcase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BasketCaseTopBar(
    onMenuClick: () -> Unit,
    onBackClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    val fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
    TopAppBar(
        title = { Text(text = "BasketCase", fontFamily = fontFamily) },
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