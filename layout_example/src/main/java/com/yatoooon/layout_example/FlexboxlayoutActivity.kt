package com.yatoooon.layout_example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import android.widget.Toast
import com.google.android.flexbox.FlexboxLayout
import com.yatoooon.coordinator_example.R
import kotlinx.android.synthetic.main.activity_flexboxlayout.*

class FlexboxlayoutActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_flexboxlayout)

        button.setOnClickListener {
            editText.text.isNullOrBlank().apply {
                when {
                    this -> Toast.makeText(
                        this@FlexboxlayoutActivity,
                        "Please Enter a number",
                        Toast.LENGTH_LONG
                    ).show()
                    else -> setTextViewInFlexbox(editText.text.toString().toInt())
                }
            }
        }
    }

    private fun setTextViewInFlexbox(number: Int) {
        flexboxlayout.removeAllViews()
        for (i in 1..number) {
            val tvNumbers = TextView(this)
            tvNumbers.text = i.toString()
            tvNumbers.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16.toFloat())
            tvNumbers.gravity = Gravity.CENTER
            tvNumbers.background = resources.getDrawable(R.drawable.circle_text_flexbox)
            tvNumbers.setTextColor(resources.getColor(android.R.color.white))

            val lpRight = FlexboxLayout.LayoutParams(
                FlexboxLayout.LayoutParams.WRAP_CONTENT,
                FlexboxLayout.LayoutParams.WRAP_CONTENT
            )
            tvNumbers.layoutParams = lpRight
            val lp = tvNumbers.layoutParams as FlexboxLayout.LayoutParams
            lp.setMargins(10, 10, 10, 10)
            tvNumbers.layoutParams = lp
            flexboxlayout.addView(tvNumbers)
        }
    }
}