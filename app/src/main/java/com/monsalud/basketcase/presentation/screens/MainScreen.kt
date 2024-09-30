package com.monsalud.basketcase.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.monsalud.basketcase.R
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.components.BottomSheetContent
import com.monsalud.basketcase.ui.theme.spacing
import org.koin.androidx.compose.koinViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    modifier: Modifier = Modifier,
) {
    val viewModel: BasketCaseViewModel = koinViewModel()
    var isBottomSheetOpen by remember { mutableStateOf(false) }

    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Shopping Lists",
            fontFamily = FontFamily(Font(R.font.playwriteitmoderna_extralight)),
            modifier = modifier.align(Alignment.TopStart)
                .padding(16.dp),
        )
        FloatingActionButton(
            onClick = { isBottomSheetOpen = true },
            modifier = Modifier.align(Alignment.BottomEnd).padding(MaterialTheme.spacing.extraLarge)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = "Add")
        }
    }
    if (isBottomSheetOpen) {
        ModalBottomSheet(
            onDismissRequest = { isBottomSheetOpen = false },
            sheetState = rememberModalBottomSheetState(),
        ) {
            BottomSheetContent()
        }
    }
}




@Composable
@Preview
fun MainScreenPreview() {
    MainScreen()
}