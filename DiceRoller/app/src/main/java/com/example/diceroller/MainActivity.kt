package com.example.diceroller

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    val start: Int = 1
    val end: Int = 6

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val rollButton: Button = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        val countUpButton: Button = findViewById(R.id.count_up_button)
        countUpButton.setOnClickListener { countUp() }
    }

    private fun rollDice() {
        val textView: TextView = findViewById(R.id.text_view)
        val randomNumber: Int = (start..end).random()
        textView.text = randomNumber.toString()
    }

    private fun countUp() {
        val textView: TextView = findViewById(R.id.text_view)
        val currentValue: Int? = textView.text.toString().toIntOrNull()

        val newValue: Int
        if (currentValue != null) {
            newValue = if (currentValue < end) currentValue + 1 else currentValue
        }else {
            newValue = start
        }
        textView.text = newValue.toString()
    }
}