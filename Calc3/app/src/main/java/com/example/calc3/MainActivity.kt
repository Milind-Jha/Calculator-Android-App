package com.example.calc3

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {

    var lastdigit: Boolean = false
    var lastDot: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun onDigit(view : View){
        val tvIn = findViewById<TextView>(R.id.tvInput)
        tvIn.append((view as Button).text)
        lastdigit=true

    }
    fun onClear(view: View){
        val tvIn = findViewById<TextView>(R.id.tvInput)
        tvIn.text=""
        lastDot=false
        lastdigit=false
    }
    fun onDecimalPoint(view: View){
        if(lastdigit && !lastDot){
            val tvIn = findViewById<TextView>(R.id.tvInput)
            tvIn.append(".")
            lastdigit=false
            lastDot=true
        }
    }

    fun onEqual(view: View){
        if (lastdigit){
            val tvIn = findViewById<TextView>(R.id.tvInput)
            var calculation = tvIn.text.toString()
            var prefix =""
            try {
                if (calculation.startsWith("-")){
                    prefix = "-"
                    calculation=calculation.substring(1)
                }
                if (calculation.contains("-")){
                    val splitExpression = calculation.split("-")

                    var firstValue = splitExpression[0]
                    var secondValue = splitExpression[1]

                    if (!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvIn.text = (firstValue.toDouble() - secondValue.toDouble()).toString()
                    if (tvIn.text.contains(".0")){
                        tvIn.text = tvIn.text.substring(0,tvIn.text.length-2)
                    }
                }
                else if (calculation.contains("+")){
                    val splitExpression = calculation.split("+")

                    var firstValue = splitExpression[0]
                    var secondValue = splitExpression[1]

                    if (!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvIn.text = (firstValue.toDouble() + secondValue.toDouble()).toString()
                    if (tvIn.text.contains(".0")){
                        tvIn.text = tvIn.text.substring(0,tvIn.text.length-2)
                    }
                }
                else if (calculation.contains("x")){
                    val splitExpression = calculation.split("x")

                    var firstValue = splitExpression[0]
                    var secondValue = splitExpression[1]

                    if (!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvIn.text = (firstValue.toDouble() * secondValue.toDouble()).toString()
                    if (tvIn.text.contains(".0")){
                        tvIn.text = tvIn.text.substring(0,tvIn.text.length-2)
                    }
                }
                else if (calculation.contains("/")){
                    val splitExpression = calculation.split("/")

                    var firstValue = splitExpression[0]
                    var secondValue = splitExpression[1]

                    if (!prefix.isEmpty()){
                        firstValue = prefix + firstValue
                    }

                    tvIn.text = (firstValue.toDouble() / secondValue.toDouble()).toString()
                }
            }
            catch (e : ArithmeticException){
//                e.printStackTrace()
            }
        }
    }

    fun onOperator(view: View){
        val tvIn = findViewById<TextView>(R.id.tvInput)
        if (lastdigit && !isOperatorAdded(tvIn.text.toString())){
            tvIn.append((view as Button).text)
            lastdigit=false
            lastDot=false
        }
    }

    private fun isOperatorAdded( string: String):Boolean{
        return if (string.startsWith("-")){
            false
        }
        else{
            string.contains("/")|| string.contains("*")||
                    string.contains("+")|| string.contains("-")
        }
    }
}