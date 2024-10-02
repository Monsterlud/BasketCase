package com.monsalud.basketcase.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.monsalud.basketcase.ui.theme.Colors.ErrorContainerDark
import com.monsalud.basketcase.ui.theme.Colors.ErrorContainerLight
import com.monsalud.basketcase.ui.theme.Colors.ErrorDark
import com.monsalud.basketcase.ui.theme.Colors.ErrorLight
import com.monsalud.basketcase.ui.theme.Colors.OnErrorContainerDark
import com.monsalud.basketcase.ui.theme.Colors.OnErrorContainerLight
import com.monsalud.basketcase.ui.theme.Colors.OnErrorDark
import com.monsalud.basketcase.ui.theme.Colors.OnPrimaryContainerDark
import com.monsalud.basketcase.ui.theme.Colors.OnPrimaryContainerLight
import com.monsalud.basketcase.ui.theme.Colors.OnPrimaryDark
import com.monsalud.basketcase.ui.theme.Colors.OnPrimaryLight
import com.monsalud.basketcase.ui.theme.Colors.OnSecondaryContainerDark
import com.monsalud.basketcase.ui.theme.Colors.OnSecondaryContainerLight
import com.monsalud.basketcase.ui.theme.Colors.OnSecondaryDark
import com.monsalud.basketcase.ui.theme.Colors.OnSecondaryLight
import com.monsalud.basketcase.ui.theme.Colors.OnTertiaryContainerDark
import com.monsalud.basketcase.ui.theme.Colors.OnTertiaryContainerLight
import com.monsalud.basketcase.ui.theme.Colors.OnTertiaryDark
import com.monsalud.basketcase.ui.theme.Colors.PrimaryContainerDark
import com.monsalud.basketcase.ui.theme.Colors.PrimaryContainerLight
import com.monsalud.basketcase.ui.theme.Colors.PrimaryDark
import com.monsalud.basketcase.ui.theme.Colors.PrimaryLight
import com.monsalud.basketcase.ui.theme.Colors.SecondaryContainerDark
import com.monsalud.basketcase.ui.theme.Colors.SecondaryContainerLight
import com.monsalud.basketcase.ui.theme.Colors.SecondaryDark
import com.monsalud.basketcase.ui.theme.Colors.SecondaryLight
import com.monsalud.basketcase.ui.theme.Colors.SurfaceDark
import com.monsalud.basketcase.ui.theme.Colors.SurfaceLight
import com.monsalud.basketcase.ui.theme.Colors.TertiaryContainerDark
import com.monsalud.basketcase.ui.theme.Colors.TertiaryContainerLight
import com.monsalud.basketcase.ui.theme.Colors.TertiaryDark
import com.monsalud.basketcase.ui.theme.Colors.TertiaryLight

private val DarkColorScheme =
    darkColorScheme(
        primary = PrimaryDark,
        onPrimary = OnPrimaryDark,
        primaryContainer = PrimaryContainerDark,
        onPrimaryContainer = OnPrimaryContainerDark,
        secondary = SecondaryDark,
        onSecondary = OnSecondaryDark,
        secondaryContainer = SecondaryContainerDark,
        onSecondaryContainer = OnSecondaryContainerDark,
        tertiary = TertiaryDark,
        onTertiary = OnTertiaryDark,
        tertiaryContainer = TertiaryContainerDark,
        onTertiaryContainer = OnTertiaryContainerDark,
        error = ErrorDark,
        onError = OnErrorDark,
        errorContainer = ErrorContainerDark,
        onErrorContainer = OnErrorContainerDark,
        surface = SurfaceDark,
    )

private val LightColorScheme =
    lightColorScheme(
        primary = PrimaryLight,
        onPrimary = OnPrimaryLight,
        primaryContainer = PrimaryContainerLight,
        onPrimaryContainer = OnPrimaryContainerLight,
        secondary = SecondaryLight,
        onSecondary = OnSecondaryLight,
        secondaryContainer = SecondaryContainerLight,
        onSecondaryContainer = OnSecondaryContainerLight,
        tertiary = TertiaryLight,
        onTertiary = OnTertiaryContainerLight,
        tertiaryContainer = TertiaryContainerLight,
        onTertiaryContainer = OnTertiaryContainerLight,
        error = ErrorLight,
        onError = OnErrorContainerLight,
        errorContainer = ErrorContainerLight,
        onErrorContainer = OnErrorContainerLight,
        surface = SurfaceLight,
    )

@Composable
fun BasketCaseTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colorScheme = if (darkTheme) DarkColorScheme else LightColorScheme

    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = (view.context as Activity).window
            window.statusBarColor = colorScheme.primary.toArgb()
            WindowCompat.getInsetsController(window, view).isAppearanceLightStatusBars = darkTheme
        }
    }

    CompositionLocalProvider(LocalSpacing provides Spacing()) {
        MaterialTheme(
            colorScheme = colorScheme,
            typography = Typography,
            content = content,
        )
    }
}
