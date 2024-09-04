package com.monsalud.basketcase

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.Scaffold
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.navigation.compose.rememberNavController
import com.monsalud.basketcase.presentation.BasketCaseTopBar
import com.monsalud.basketcase.presentation.BasketCaseViewModel
import com.monsalud.basketcase.presentation.navigation.BasketCaseNavigation
import com.monsalud.basketcase.presentation.navigation.NavDrawer
import com.monsalud.basketcase.ui.theme.BasketCaseTheme
import kotlinx.coroutines.launch
import org.koin.androidx.scope.scope
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : ComponentActivity() {
    private val viewModel: BasketCaseViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        setContent {
            BasketCaseTheme {
                val navController = rememberNavController()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val scope = rememberCoroutineScope()
                
                NavDrawer(drawerState = drawerState, navController = navController, onScreenChange = { screen ->
                    viewModel.setCurrentScreen(screen)
                }) {
                    Scaffold(
                        topBar = {
                            BasketCaseTopBar(
                                onMenuClick = {
                                    scope.launch {
                                        drawerState.open()
                                    }
                                },
                                onBackClick = {
                                    navController.navigateUp()
                                }
                            )
                        }
                    ) {
                        BasketCaseNavigation(
                            navController = navController,
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
}
