package com.example.calcultor.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavScreen(val route: String, val title: String, val icon: ImageVector) {
    object Calculator : BottomNavScreen("calculator", "Calculator", Icons.Default.Home)
    object Settings : BottomNavScreen("settings", "Settings", Icons.Default.Settings)
}
