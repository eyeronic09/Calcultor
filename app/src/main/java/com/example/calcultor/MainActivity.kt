package com.example.calcultor

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.calcultor.homeScreen.presentationLayer.calcutatorVM
import com.example.calcultor.navigation.BottomNavScreen
import com.example.calcultor.navigation.MainScreenWithBottomBar
import com.example.calcultor.ui.theme.CalculatorTheme
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            enableEdgeToEdge()
            CalculatorTheme {
                    val calculatorVM = remember { calcutatorVM() }
                    MainScreenWithBottomBar(calculatorVM)
            }
        }
    }
}
