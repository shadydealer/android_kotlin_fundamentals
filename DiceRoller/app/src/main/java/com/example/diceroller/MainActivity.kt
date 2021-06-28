package com.example.diceroller

import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import androidx.core.content.res.ResourcesCompat

class MainActivity : AppCompatActivity() {
    private val start = 1
    private val end: Int = 6
    private val resourcePathPrefix: String = "drawable/dice_"

    private lateinit var countUpButton: Button
    private lateinit var rollButton: Button
    private lateinit var imageView: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        countUpButton = findViewById(R.id.count_up_button)
        countUpButton.setOnClickListener { countUp() }

        imageView = findViewById(R.id.dice_image)
    }

    private fun rollDice() {
        val randomNumber: Int = (start..end).random()
        setImageView(randomNumber)
    }

    private fun countUp() {
        val currentValue: String? = imageView.tag as String?
        if (currentValue != null) {
            val matchResults: MatchResult? = """$resourcePathPrefix(\d)"""
                .toRegex()
                .matchEntire(currentValue)
            val side: Int = matchResults!!.groups[1]!!.value.toInt()
            if (side < end) {
                setImageView(side+1)
            }
        }else {
            setImageView(1)
        }
    }

    private fun setImageView(number: Int) {
        val resourcePath = "$resourcePathPrefix$number"
        val imageResource: Int = resources.getIdentifier(
            resourcePath,
            null,
            packageName
        )
        val drawableImage: Drawable? = ResourcesCompat.getDrawable(
            resources,
            imageResource,
            null
        )

        if (drawableImage != null) {
            imageView.setImageDrawable(drawableImage)
            imageView.tag = "$resourcePathPrefix$number"
        }
    }
}