package com.example.calcultor.homeScreen.presentationLayer

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

@OptIn(ExperimentalMaterial3Api::class)
@Composable
@Preview(showSystemUi = true)
fun Calculator_screen() {
    Scaffold(
       topBar = {
           TopAppBar(title = {Text("calculator")})
       }
    ){ paddingValues ->
        var expression by remember { mutableStateOf("") }
        var result by remember { mutableStateOf("") }

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = expression, fontSize = 32.sp)
            Text(text = result, fontSize = 48.sp, fontWeight = FontWeight.Bold)

            val buttons = listOf(
                listOf("7", "8", "9", "/"),
                listOf("4", "5", "6", "*"),
                listOf("1", "2", "3", "-"),
                listOf("0", ".", "=", "+"),
                listOf("C")
            )

            buttons.forEach { row ->
                Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceEvenly) {
                    row.forEach { label ->
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
                            modifier = Modifier
                                .padding(8.dp)
                                .weight(1f)
                        ) {
                            Text(label, fontSize = 24.sp)
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