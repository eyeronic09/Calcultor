package com.example.calcultor.homeScreen.presentationLayer

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.sharp.Clear
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ModifierLocalBeyondBoundsLayout
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcultor.R

import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showBackground = true)
fun Calculator_screen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator") }
            )
        }
    ) { paddingValues ->
        var expression by rememberSaveable { mutableStateOf("") }
        var result by rememberSaveable { mutableStateOf("") }
        val NotingFonts = FontFamily(
            Font(R.font.jd_lcd_rounded)
        )

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {

            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = expression,
                    fontFamily = NotingFonts,
                    textAlign = TextAlign.Center,
                    fontSize = 75.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = result,
                    fontFamily = NotingFonts,
                    fontSize = 50.sp,
                    fontWeight = FontWeight.Bold,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 16.dp),
                    textAlign = TextAlign.Center
                )
            }
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
                            val textColor = if (label == "AC") Color.Red else Color.White

                            Button(
                                onClick = {
                                    when (label) {
                                        "=" -> result = evaluateExpression(expression)
                                        else -> expression += label
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
            Button(
                onClick = { expression = "" }, modifier = Modifier.fillMaxWidth(),
                colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
            ) {
                Text("clear", fontFamily = NotingFonts, fontSize = 48.sp)
            }

        }
    }
}

fun evaluateExpression(expression: String): String {
    return try {
        val rhino = Context.enter()
        rhino.optimizationLevel = -1
        val scope: Scriptable = rhino.initStandardObjects()
        val result = rhino.evaluateString(scope, expression, "JavaScript", 1, null)
        result.toString()
    } catch (e: Exception) {
        "Error"
    } finally {
        Context.exit()
    }
}
