package com.monsalud.basketcase.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BasicAlertDialog
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import com.monsalud.basketcase.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InstructionsDialog(
    onDismiss: () -> Unit
) {
    BasicAlertDialog(
        onDismissRequest = onDismiss,

        ) {
        Surface(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(16.dp),
            color = MaterialTheme.colorScheme.surface,
            tonalElevation = 8.dp
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Welcome to BasketCase!",
                    style = MaterialTheme.typography.titleLarge,
                    fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "BasketCase is a simple, yet powerful app that allows you to create and manage your shopping lists",
                    style = MaterialTheme.typography.titleMedium,
                    fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "ITEMS: add, remove, or edit items that you might want to purchase in the future",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "MARKETS: add, remove, or edit your favorite places to shop",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "SHOPPING LISTS: create and manage your shopping lists. As an example, you may have one weekly grocery list and another list for a special dinner party.",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
                )
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = "BASKET: this is your all-in-one shopping list that you can filter by market or by list(s). When you are at a specific market to shop for that dinner party, just filter the list to that market and that list. Your basket is now focused on only what you need to see.",
                    style = MaterialTheme.typography.titleSmall,
                    fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
                )
            }
        }
    }
}