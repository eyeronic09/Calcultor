package com.example.calcultor.homeScreen.presentationLayer

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcultor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator_screen(calculatorVM: calcutatorVM) {
    Scaffold() { paddingValues ->
        val NotingFonts = FontFamily(Font(R.font.jd_lcd_rounded))
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp)
        ) {
            // Display section
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = calculatorVM.expression,
                    fontFamily = NotingFonts,
                    textAlign = TextAlign.Center,
                    fontSize = 75.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = calculatorVM.result,
                    fontFamily = NotingFonts,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }

            // Button grid
            val buttons = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("0", ".", "=", "+")
            )
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(2f),
                verticalArrangement = Arrangement.Bottom
            ) {
                buttons.forEach { row ->
                    Row(
                        Modifier
                            .fillMaxWidth()
                            .padding(vertical = 4.dp),
                        horizontalArrangement = Arrangement.SpaceEvenly
                    ) {
                        row.forEach { label ->
                            val backgroundColor = when (label) {
                                "/", "*", "-", "+", "=" -> Color(0xFFFF3B30)
                                else -> Color(0xFF1C1C1E)
                            }
                            val textColor = Color.White

                            Button(
                                onClick = {
                                    when (label) {
                                        "=" -> calculatorVM.result =
                                            calculatorVM.evaluateExpression(calculatorVM.expression)
                                        else -> calculatorVM.expression += label
                                    }
                                },
                                colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                                modifier = Modifier
                                    .clip(CircleShape)
                                    .size(70.dp)
                            ) {
                                Text(
                                    text = label,
                                    fontSize = 54.sp,
                                    color = textColor,
                                    fontFamily = NotingFonts
                                )
                            }
                        }
                    }
                }
            }
            // Clear and Backspace row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Button(
                    onClick = {
                        calculatorVM.expression = ""
                        calculatorVM.result = ""
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
                ) {
                    Text("Clear", fontFamily = NotingFonts, fontSize = 32.sp)
                }
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (calculatorVM.expression.isNotEmpty())
                            calculatorVM.expression = calculatorVM.expression.dropLast(1)
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
                ) {
                    Text("Backspace", fontFamily = NotingFonts, fontSize = 32.sp, textAlign = TextAlign.Center, maxLines = 1)
                }
            }
        }
    }
}

