package com.example.homworktest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
class MainActivity : AppCompatActivity() {
    lateinit var edit1 : EditText
    lateinit var btn1 : Button
    lateinit var textView1: TextView
    lateinit var result : String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        edit1 = findViewById<EditText>(R.id.editText1)
        btn1 = findViewById(R.id.btn1)
        textView1 = findViewById(R.id.textView1)
        btn1.setOnClickListener{
            result = edit1.text.toString()
            textView1.text = result.toString()
            false
        }
    }
}