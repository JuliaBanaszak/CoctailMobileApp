package com.example.cocktailmobileapp.ui.theme

import android.app.Activity
import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import android.widget.Switch
import androidx.compose.ui.viewinterop.AndroidView
import androidx.appcompat.content.res.AppCompatResources
import com.example.cocktailmobileapp.R



private val DarkColorScheme = darkColorScheme(
    background = DarkGrey,
    surface = DarkGrey,
    onBackground = WhiteRice,
    onSurface = WhiteRice
)

private val LightColorScheme = lightColorScheme(
    background = WhiteRice,
    surface = WhiteRice,
    onBackground = Black,
    onSurface = Black
)

@Composable
fun CocktailMobileAppTheme(
    useDarkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable() () -> Unit
) {
    val colors = if (!useDarkTheme) {
        LightColorScheme
    } else {
        DarkColorScheme
    }

    MaterialTheme(
        colorScheme = colors,
        content = content
    )
}