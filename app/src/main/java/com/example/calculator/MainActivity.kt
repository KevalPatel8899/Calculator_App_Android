package com.example.calculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception
import java.sql.BatchUpdateException

class MainActivity : AppCompatActivity() {
    var value : String = ""
    var result : Double? = null
    var operation : String = ""


    fun factor100(n: Number) = n.toDouble() % 100.0 == 0.0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Number buttons
        val b0 = findViewById<Button>(R.id.button0)
        b0.setOnClickListener(numberEvent())
        val b1 = findViewById<Button>(R.id.button1)
        b1.setOnClickListener(numberEvent())
        val b2 = findViewById<Button>(R.id.button2)
        b2.setOnClickListener(numberEvent())
        val b3 = findViewById<Button>(R.id.button3)
        b3.setOnClickListener(numberEvent())
        val b4 = findViewById<Button>(R.id.button4)
        b4.setOnClickListener(numberEvent())
        val b5 = findViewById<Button>(R.id.button5)
        b5.setOnClickListener(numberEvent())
        val b6 = findViewById<Button>(R.id.button6)
        b6.setOnClickListener(numberEvent())
        val b7 = findViewById<Button>(R.id.button7)
        b7.setOnClickListener(numberEvent())
        val b8 = findViewById<Button>(R.id.button8)
        b8.setOnClickListener(numberEvent())
        val b9 = findViewById<Button>(R.id.button9)
        b9.setOnClickListener(numberEvent())

        //Event Operation Buttons
        val plusButton = findViewById<Button>(R.id.buttonPlusOperation)
        plusButton.setOnClickListener(operationEven())
        val minusButton = findViewById<Button>(R.id.buttonMinusOperation)
        minusButton.setOnClickListener(operationEven())
        val resetButton = findViewById<Button>(R.id.buttonRenewOperation)
        resetButton.setOnClickListener(operationEven())
        val plusMinusButton = findViewById<Button>(R.id.buttonPlusAndMinus)
        plusMinusButton.setOnClickListener(operationEven())
        val multiplicationButton = findViewById<Button>(R.id.buttonMultiplicationOperation)
        multiplicationButton.setOnClickListener(operationEven())
        val divisionButton =  findViewById<Button>(R.id.buttonDivisionOperation)
        divisionButton.setOnClickListener(operationEven())
        val decimalButton = findViewById<Button>(R.id.buttonDecimal)
        decimalButton.setOnClickListener(operationEven())
        val completionButton = findViewById<Button>(R.id.buttonCompiltionOperation)
        completionButton.setOnClickListener(operationEven())
        val backspaceButton = findViewById<Button>(R.id.buttonBackspaceOperation)
        backspaceButton.setOnClickListener(operationEven())
        val percentageButton = findViewById<Button>(R.id.buttonPercentageOperation)
        percentageButton.setOnClickListener(operationEven())
    }


    private fun operationEven(): View.OnClickListener{
        return View.OnClickListener { v :View ->
            when(v.id){
                R.id.buttonPlusOperation -> {
                    completionOperation()
                    operation = "+"
                    value = ""
                }
                R.id.buttonMinusOperation-> {
                    completionOperation()
                    operation = "-"
                    value = ""
                }
                R.id.buttonPlusAndMinus -> {
                    if(!value.equals("")){
                        if(value.contains("-")){
                            value = value.removePrefix("-")
                            textViewNumberDisplay.text = value
                        }else{
                            value = "-$value"
                            textViewNumberDisplay.text = value
                        }
                    }
                }
                R.id.buttonPercentageOperation ->{
                    if(result == null &&  value == ""){
                        Toast.makeText(this, "Please add some value", Toast.LENGTH_LONG).show()
                    }else if(result == null && value != ""){
                        result = value.toDouble()
                        result =  result!! / 100
                        textViewNumberDisplay.text = result.toString()
                        value = ""
                    }else if(result != null && value !=  ""){
                        completionOperation()
                        operation = ""
                        value = ""
                        result = result!!/100
                        textViewNumberDisplay.text = result.toString()
                    }else if(result != null && value == ""){
                        result = result!! / 100
                        textViewNumberDisplay.text = result.toString()
                    }
                }
                R.id.buttonRenewOperation -> {
                    value = ""
                    result = null
                    textViewNumberDisplay.text = value
                }
                R.id.buttonBackspaceOperation -> {
                    if(value != ""){
                        value = value.substring(0 , value.length - 1)
                        textViewNumberDisplay.text = (value)
                    }else{
                        Toast.makeText(this, "There is no value on screen", Toast.LENGTH_LONG).show()
                    }
                }
                R.id.buttonMultiplicationOperation ->{
                    completionOperation()
                    operation = "*"
                    value = ""

                }
                R.id.buttonDivisionOperation -> {
                    completionOperation()
                    operation = "/"
                    value = ""
                }
                R.id.buttonCompiltionOperation -> {
                    completionOperation()
                }
                R.id.buttonDecimal -> {
                    if(value.equals("")){
                        Toast.makeText(this, "Please add some value", Toast.LENGTH_LONG).show()
                    }else{
                        if(value.contains(".")){
                            Toast.makeText(this, "Decimal place is already available", Toast.LENGTH_LONG).show()
                        }else{
                            value += "."
                            textViewNumberDisplay.text = value
                        }
                    }
                }
            }
        }
    }

    private fun completionOperation(){
        if(value != ""){
            if(result != null){
                if(operation == "+"){
                    result = result!! + (value.toDouble())
                    textViewNumberDisplay.setText(result.toString())
                }
                if(operation == "-"){
                    result = result!! - (value.toDouble())
                    textViewNumberDisplay.setText(result.toString())
                }
                if(operation == "*"){
                    result = result!! * (value.toDouble())
                    textViewNumberDisplay.setText(result.toString())
                }
                if(operation == "/"){
                    if(value.toDouble() != 0.0){
                        result = result!! / (value.toDouble())
                        textViewNumberDisplay.setText(result.toString())
                    }else{
                        Toast.makeText(this, "Not possible to divide by 0", Toast.LENGTH_LONG).show()
                        value = ""
                        textViewNumberDisplay.setText(value)
                    }
                }
            }else{
                result = value.toDouble()
            }
        }else{
            Toast.makeText(this,"Please add some value", Toast.LENGTH_LONG).show()
        }
    }
    private fun numberEvent(): View.OnClickListener{
        return View.OnClickListener { v: View ->
            when(v.id){
            R.id.button0 ->{ value += "0"
                textViewNumberDisplay.text = value
            }
            R.id.button1 -> {value += "1"
                textViewNumberDisplay.text = value
            }
            R.id.button2 ->{ value += "2"
                textViewNumberDisplay.text = value
            }
            R.id.button3 ->{ value += "3"
                textViewNumberDisplay.text = value
            }
            R.id.button4 -> {value += "4"
                textViewNumberDisplay.text = value
            }
            R.id.button5 ->{ value += "5"
                textViewNumberDisplay.text = value
            }
            R.id.button6 ->{ value += "6"
                textViewNumberDisplay.text = value
            }
            R.id.button7 -> {value += "7"
                textViewNumberDisplay.text = value
            }
            R.id.button8 -> {value += "8"
                textViewNumberDisplay.text = value
            }
            R.id.button9 -> {value += "9"
                textViewNumberDisplay.text = value
            }
            }
        }
    }
}