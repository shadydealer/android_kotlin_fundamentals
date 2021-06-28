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

    private lateinit var rollButton: Button
    private lateinit var diceImages: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rollButton = findViewById(R.id.roll_button)
        rollButton.setOnClickListener { rollDice() }

        diceImages = arrayOf(
            findViewById(R.id.dice_image_1),
            findViewById(R.id.dice_image_2)
        )
    }

    private fun rollDice() {
        for(diceImage in diceImages) {
            val randomNumber: Int = (start..end).random()
            setImageView(diceImage, randomNumber)
        }
    }

    private fun setImageView(diceImage: ImageView, number: Int) {
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
            diceImage.setImageDrawable(drawableImage)
        }
    }
}