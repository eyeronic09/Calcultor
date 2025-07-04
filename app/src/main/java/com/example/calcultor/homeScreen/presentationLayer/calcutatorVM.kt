package com.example.calcultor.homeScreen.presentationLayer

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import org.mozilla.javascript.Context
import org.mozilla.javascript.Scriptable

class calcutatorVM : ViewModel() {

    var expression by mutableStateOf("")
    var result by mutableStateOf("")

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

}