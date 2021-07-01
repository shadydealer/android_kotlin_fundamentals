package com.example.colormyviews

import android.databinding.DataBindingUtil
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintLayout
import android.support.v4.content.res.ResourcesCompat
import android.view.View
import com.example.colormyviews.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var backgroundColors: Array<Drawable?>
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        backgroundColors = arrayOf(
            ResourcesCompat.getDrawable(resources, R.color.my_green, null),
            ResourcesCompat.getDrawable(resources, R.color.my_red, null),
            ResourcesCompat.getDrawable(resources, R.color.my_yellow, null)
        )
        setListeners()
    }
    private fun makeColored(view: View) {
        view.background = getNextBackgroundColor(view)
    }

    private fun setListeners() {

        val layout = binding.constraintLayout

        val childrenCount: Int = layout.childCount
        for (c in 0 until childrenCount) {
            val view: View = layout.getChildAt(c)
            view.setOnClickListener { makeColored(it) }
        }

        layout.setOnClickListener{ makeColored(it)}
    }

    private fun getNextBackgroundColor(view: View): Drawable? {
        val color: ColorDrawable? = view.background as? ColorDrawable
        val i: Int = backgroundColors.indexOf(color)

        val nextBackgroundColor: Int = (i + 1) % backgroundColors.size
        return  backgroundColors[nextBackgroundColor]
    }

}