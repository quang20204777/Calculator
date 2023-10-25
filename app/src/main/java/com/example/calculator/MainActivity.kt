package com.example.calculator

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {
    private lateinit var inputOutput: TextView
    private var firstOperand: Int = 0
    private var operator: String? = null
    private var secondOperand: Int = 0
    private var isResultDisplayed: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        inputOutput = findViewById(R.id.result_tv)

        val numberButtons = arrayOf(
            findViewById<Button>(R.id.button_zero),
            findViewById(R.id.button_one),
            findViewById(R.id.button_two),
            findViewById(R.id.button_three),
            findViewById(R.id.button_four),
            findViewById(R.id.button_five),
            findViewById(R.id.button_six),
            findViewById(R.id.button_seven),
            findViewById(R.id.button_eight),
            findViewById(R.id.button_nine)
        )

        val operatorButtons = arrayOf(
            findViewById<Button>(R.id.button_devide),
            findViewById(R.id.button_multi),
            findViewById(R.id.button_sub),
            findViewById(R.id.button_plus)
        )

        numberButtons.forEach { button ->
            button.setOnClickListener { onNumberClick(button) }
        }

        operatorButtons.forEach { button ->
            button.setOnClickListener { onOperatorClick(button) }
        }

        val clearEntryButton = findViewById<Button>(R.id.button_ce)
        val clearButton = findViewById<Button>(R.id.button_c)
        val backspaceButton = findViewById<Button>(R.id.button_bs)
        val equalButton = findViewById<Button>(R.id.button_equal)

        clearEntryButton.setOnClickListener { onClearEntryClick() }
        clearButton.setOnClickListener { onClearClick() }
        backspaceButton.setOnClickListener { onBackspaceClick() }
        equalButton.setOnClickListener { onEqualClick() }
    }

    private fun onNumberClick(button: View) {
        if (isResultDisplayed) {
            inputOutput.text = ""
            isResultDisplayed = false
        }

        if(inputOutput.text == "0") inputOutput.text = ""
        val number = (button as Button).text.toString()
        val currentText = inputOutput.text.toString()
        inputOutput.text = currentText + number
    }

    private fun onOperatorClick(button: View) {
        if (isResultDisplayed) {
            isResultDisplayed = false
        }

        operator = (button as Button).text.toString()
        firstOperand = inputOutput.text.toString().toInt()
        inputOutput.text = ""
    }

    private fun onClearEntryClick() {
        inputOutput.text = "0"
    }

    private fun onClearClick() {
        inputOutput.text = "0"
        firstOperand = 0
        operator = null
        secondOperand = 0
        isResultDisplayed = false
    }

    private fun onBackspaceClick() {
        val currentText = inputOutput.text.toString()
        if (currentText.length > 1) {
            inputOutput.text = currentText.substring(0, currentText.length - 1)
        } else {
            inputOutput.text = "0"
        }
    }

    private fun onEqualClick() {
        if (operator != null) {
            val currentText = inputOutput.text.toString()
            secondOperand = currentText.toInt()
            val result = when (operator) {
                "+" -> firstOperand + secondOperand
                "-" -> firstOperand - secondOperand
                "*" -> firstOperand * secondOperand
                "/" -> {
                    if (secondOperand != 0) {
                        firstOperand / secondOperand
                    } else {
                        inputOutput.text = "Error"
                        return
                    }
                }
                else -> {
                    inputOutput.text = "Error"
                    return
                }
            }
            inputOutput.text = result.toString()
            firstOperand = result
            operator = null
            isResultDisplayed = true
        }
    }
}