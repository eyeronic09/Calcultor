package com.example.calcultor

import Calculator_screen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.calcultor.homeScreen.presentationLayer.calcutatorVM
import com.example.calcultor.ui.theme.CalcultorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CalcultorTheme {
                val viewM = calcutatorVM()
                Calculator_screen(viewM)
            }
        }
    }
}
