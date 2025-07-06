package com.example.calcultor.navigation

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.calcultor.UnitScreen.ListOfConversion
import com.example.calcultor.homeScreen.presentationLayer.Calculator_screen
import com.example.calcultor.homeScreen.presentationLayer.calcutatorVM

@Composable
fun MainScreenWithBottomBar(calculatorVM: calcutatorVM) {
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            BottomNavigationBar(navController = navController)
        }
    ) { innerPadding ->
        NavHost(
            navController = navController,
            startDestination = BottomNavScreen.Calculator.route,
            modifier = Modifier.padding(innerPadding)
        ) {
            composable(BottomNavScreen.Calculator.route) {
                Calculator_screen(calculatorVM)
            }
            composable(BottomNavScreen.UnitConverter.route) {
                ListOfConversion()
            }

        }
    }
}
