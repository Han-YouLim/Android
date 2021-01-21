package com.example.bmicalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class ResultActivity : AppCompatActivity() {
    lateinit var resultTextView: TextView
    lateinit var imageView: ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        resultTextView = findViewById(R.id.resultTextView)
        imageView = findViewById(R.id.imageView)

        var height = intent.getStringExtra("height")?.toInt()
        var weight = intent.getStringExtra("weight")?.toInt()
        var name = intent.getStringExtra("name")

        val bmi = weight!! / Math.pow((height!! /100.0), 2.0)

        when{
            bmi >= 35 -> resultTextView.text="고도비만"
            bmi >= 30 -> resultTextView.text="2단계 비만"
            bmi >= 25 -> resultTextView.text="1단계 비만"
            bmi >= 20 -> resultTextView.text="과체중"
            bmi >= 18.5 -> resultTextView.text="정상"
            else -> resultTextView.text="저체중"
        }

        when{
            bmi>=23 ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_very_dissatisfied_24
                )
            bmi>=18.5 ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sentiment_satisfied_alt_24
                )
            else ->
                imageView.setImageResource(
                    R.drawable.ic_baseline_sick_24
                )

        }

        Toast.makeText(this, "$name"+ " : "+ "$bmi", Toast.LENGTH_SHORT).show()
    }
}