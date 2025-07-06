package com.example.calcultor.UnitScreen

sealed class ConversionType(val title: String, val unitLabel: String) {
    object OunceToGram : ConversionType("Ounce to Gram", "oz ➔ g")
    object KilogramToPound : ConversionType("Kilogram to Pound", "kg ➔ lbs")
    object MeterToFeet : ConversionType("Meter to Feet", "m ➔ ft")
    object CelsiusToFahrenheit : ConversionType("Celsius to Fahrenheit", "°C ➔ °F")

    companion object {
        val allTypes = listOf(OunceToGram, KilogramToPound, MeterToFeet, CelsiusToFahrenheit)
    }
}
