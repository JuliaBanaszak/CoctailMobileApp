package com.example.cocktailmobileapp.ui.theme

import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.foundation.layout.*
import androidx.compose.material3.icons.Icons
import androidx.compose.material3.icons.filled.LightMode
import androidx.compose.material3.icons.filled.DarkMode
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.vector.ImageVector

@Composable
fun ThemeSwitch(isChecked: Boolean, onCheckedChange: (Boolean) -> Unit) {
    val icon: @Composable () -> Unit = {
        Icon(
            imageVector = if (isChecked) Icons.Default.DarkMode else Icons.Default.LightMode,
            contentDescription = null,
            modifier = Modifier.size(SwitchDefaults.IconSize),
        )
    }

    Switch(
        checked = isChecked,
        onCheckedChange = onCheckedChange,
        thumbContent = icon
    )
}

