package com.monsalud.basketcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Scaffold
import androidx.navigation.compose.rememberNavController
import com.monsalud.basketcase.presentation.BasketCaseTopBar
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.navigation.BasketCaseNavigation
import com.monsalud.basketcase.ui.theme.BasketCaseTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: BasketCaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BasketCaseTheme {
                val navController = rememberNavController()

                Scaffold(
                    topBar = { BasketCaseTopBar(
                        onBackClick = {
                            navController.navigateUp()
                        }
                    ) }
                ) {
                    BasketCaseNavigation(
                        innerPadding = it,
                        onScreenChange = { screen ->
                            viewModel.setCurrentScreen(screen)
                        }
                    )
                }
            }
        }
    }
}
