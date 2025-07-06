package com.example.calcultor.UnitScreen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absolutePadding
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp // Not used directly here, but often in typography
import com.example.calcultor.R

@Preview(showSystemUi = true)
@Composable
fun ListOfConversion() {
    val NotingFonts = FontFamily(Font(R.font.jd_lcd_rounded))
    val conversionsMap = mapOf(
        "Ounce to Gram" to "oz ➔ g",
        "Kilogram to Pound" to "kg ➔ lbs",
        "Meter to Feet" to "m ➔ ft",
        "Celsius to Fahrenheit" to "°C ➔ °F"
    )
    val conversionList = conversionsMap.entries.toList()

    LazyColumn(
        modifier = Modifier
            .systemBarsPadding()
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        items(conversionList) { mapEntry ->
            Card(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
            ) {
                Column(
                    modifier = Modifier
                        .padding(16.dp)
                        .fillMaxWidth()
                ) {
                    Text(
                        text = mapEntry.key,
                        fontSize =  40.sp,
                        fontFamily = NotingFonts
                    )
                    Spacer(modifier = Modifier.height(40.dp))
                    Text(
                        modifier = Modifier.fillMaxWidth(),
                        text = mapEntry.value,
                        textAlign = TextAlign.End,
                        fontFamily = NotingFonts
                    )
                }
            }
        }
    }
}
