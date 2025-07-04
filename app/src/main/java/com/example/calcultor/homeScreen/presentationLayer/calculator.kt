import com.example.calcultor.homeScreen.presentationLayer.calcutatorVM



import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.calcultor.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Calculator_screen(calcutatorVM: calcutatorVM) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Calculator") }
            )
        }
    ) { paddingValues ->
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
                    text = calcutatorVM.expression,
                    fontFamily = NotingFonts,
                    textAlign = TextAlign.Center,
                    fontSize = 75.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(bottom = 8.dp)
                )
                Spacer(Modifier.height(20.dp))
                Text(
                    text = calcutatorVM.result,
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
                                        "=" -> calcutatorVM.result =
                                            calcutatorVM.evaluateExpression(calcutatorVM.expression)
                                        else -> calcutatorVM.expression += label
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
                        calcutatorVM.expression = ""
                        calcutatorVM.result = ""
                    },
                    modifier = Modifier.weight(1f),
                    colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFFF3B30))
                ) {
                    Text("Clear", fontFamily = NotingFonts, fontSize = 32.sp)
                }
                Spacer(Modifier.width(8.dp))
                Button(
                    onClick = {
                        if (calcutatorVM.expression.isNotEmpty())
                            calcutatorVM.expression = calcutatorVM.expression.dropLast(1)
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

@Preview(showSystemUi = true)
@Composable
fun previews() {
    val viewM = calcutatorVM()
    Calculator_screen(viewM)
}