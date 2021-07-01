package com.example.diceroller

import android.content.Context
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.core.content.res.ResourcesCompat
import androidx.databinding.DataBindingUtil
import com.example.diceroller.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val start = 1
    private val end: Int = 6
    private val resourcePathPrefix: String = "drawable/dice_"
    private lateinit var binding: ActivityMainBinding
    private lateinit var diceImages: Array<ImageView>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)

        binding.rollButton.setOnClickListener { rollDice(it) }
        binding.diceToRollCount.setOnEditorActionListener {v, actionId, _ ->
            if(actionId == EditorInfo.IME_ACTION_DONE) {
                rollDice(v)
                true
            } else {
                false
            }
        }
    }

    private fun rollDice(view: View) {
        binding.diceImagesLayout.removeAllViews()
        generateDiceImages()
        for (diceImage in diceImages) {
            val randomNumber: Int = (start..end).random()
            setImageView(diceImage, randomNumber)
        }
        hideKeyboard(view)
    }

    private fun hideKeyboard(view: View) {
        val inputMethodManager =
            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
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

    private fun generateDiceImages() {
        val count: Int = binding.diceToRollCount.text.toString().toIntOrNull() ?: 0
        diceImages = Array(count) {
            val imageView: ImageView = buildDiceImage()
            binding.diceImagesLayout.addView(imageView)
            imageView
        }
    }

    private fun buildDiceImage(): ImageView {
        val imageView = ImageView(this)
        imageView.layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        ).apply {
            gravity = Gravity.CENTER_HORIZONTAL
        }
        imageView.setImageDrawable(
            ResourcesCompat.getDrawable(
                resources,
                R.drawable.empty_dice,
                null
            )
        )
        return imageView
    }
}
