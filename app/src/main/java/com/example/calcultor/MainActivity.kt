package com.example.calcultor

import com.example.calcultor.homeScreen.presentationLayer.Calculator_screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.calcultor.homeScreen.presentationLayer.calcutatorVM
import com.example.calcultor.navigation.MainScreenWithBottomBar
import com.example.calcultor.ui.theme.CalcultorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcultorTheme {
                setContent {
                    val calculatorVM = remember { calcutatorVM() }
                    MainScreenWithBottomBar(calculatorVM)
                }

            }
        }
    }
}
@Composable
fun SettingsScreen() {
    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text("Settings Screen")
    }
}