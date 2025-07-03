package com.example.calcultor.homeScreen.presentationLayer

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator_screen() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(containerColor = Color.Black)
            )
        }
    ) { paddingValues ->
        var expression by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .padding(16.dp),
            verticalArrangement = Arrangement.Bottom
        ) {
            Text(
                text = expression,
                fontSize = 32.sp,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 8.dp),
                textAlign = TextAlign.End,
                color = Color.White
            )
            Text(
                text = result,
                fontSize = 48.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = 16.dp),
                textAlign = TextAlign.End,
                color = Color.White
            )

            val buttons = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("0", ".", "=", "+"),
                listOf("C")
            )

            buttons.forEach { row ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    row.forEach { label ->
                        val backgroundColor = when (label) {
                            "C" -> Color.White
                            "/", "*", "-", "+", "=" -> Color(0xFFFF3B30) // Red tone
                            else -> Color(0xFF1C1C1E) // Deep gray/black
                        }
                        val textColor = if (label == "C") Color.Red else Color.White

                        Button(
                            onClick = {
                                when (label) {
                                    "=" -> result = evaluateExpression(expression)
                                    "C" -> {
                                        expression = ""
                                        result = ""
                                    }
                                    else -> expression += label
                                }
                            },
                            colors = ButtonDefaults.buttonColors(containerColor = backgroundColor),
                            modifier = Modifier
                                .clip(CircleShape)
                                .size(70.dp)
                        ) {
                            Text(text = label, fontSize = 24.sp, color = textColor)
                        }
                    }
                }
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
