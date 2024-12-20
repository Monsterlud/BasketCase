package com.monsalud.basketcase.presentation.navigation

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.outlined.List
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.NavigationDrawerItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.monsalud.basketcase.R
import com.monsalud.basketcase.ui.theme.spacing
import kotlinx.coroutines.launch

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NavDrawer(
    drawerState: DrawerState,
    navController: NavHostController,
    onScreenChange: (Screen) -> Unit,
    content: @Composable () -> Unit,
) {
    val scope = rememberCoroutineScope()
    var selectedItemIndex by rememberSaveable {
        mutableIntStateOf(0)
    }

    val items =
        listOf(
            NavigationItem(
                title = "Shopping Lists",
                selectedIcon = Icons.Filled.List,
                unselectedIcon = Icons.Outlined.List,
                badgeCount = 45,
                route = Screen.MainScreen.route,
            ),
            NavigationItem(
                title = "Basket",
                selectedIcon = ImageVector.vectorResource(id = R.drawable.shoppingbasket),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.shoppingbasket),
                route = Screen.GroceryBasketScreen.route,
            ),
            NavigationItem(
                title = "Pantry",
                selectedIcon = ImageVector.vectorResource(id = R.drawable.groceries),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.groceries),
                route = Screen.PantryEssentialsScreen.route,
            ),
            NavigationItem(
                title = "Markets",
                selectedIcon = ImageVector.vectorResource(id = R.drawable.market),
                unselectedIcon = ImageVector.vectorResource(id = R.drawable.market),
                route = Screen.MarketsScreen.route,
            ),
        )

    val fontFamily = FontFamily(Font(R.font.playwriteitmoderna_regular))
    ModalNavigationDrawer(
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet {
                Text(
                    text = "BasketCase",
                    fontSize = 24.sp,
                    fontFamily = fontFamily,
                    modifier = Modifier.padding(horizontal = MaterialTheme.spacing.extraLarge, vertical = MaterialTheme.spacing.large),
                )
                Divider()
                Spacer(modifier = Modifier.height(24.dp))
                items.forEachIndexed { index, item ->
                    NavigationDrawerItem(
                        label = { Text(text = item.title) },
                        selected = index == selectedItemIndex,
                        onClick = {
                            navController.navigate(item.route)
                            selectedItemIndex = index
                            scope.launch {
                                drawerState.close()
                            }
                        },
                        icon = {
                            Icon(
                                imageVector =
                                    if (index == selectedItemIndex) {
                                        item.selectedIcon
                                    } else {
                                        item.unselectedIcon
                                    },
                                contentDescription = item.title,
                            )
                        },
                        badge = {
                            if (item.badgeCount != null) {
                                Text(text = item.badgeCount.toString())
                            }
                        },
                        modifier = Modifier.padding(NavigationDrawerItemDefaults.ItemPadding),
                    )
                }
            }
        },
        content = content,
    )
}
