package com.example.pluscalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.pluscalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        var new = "0"
        var old = "0"

        binding.one.setOnClickListener {
            new = new + "1"
            binding.result.setText(new)
        }
        binding.two.setOnClickListener {
            new = new + "2"
            binding.result.setText(new)
        }
        binding.three.setOnClickListener {
            new = new + "3"
            binding.result.setText(new)
        }
        binding.four.setOnClickListener {
            new = new + "4"
            binding.result.setText(new)
        }
        binding.five.setOnClickListener {
            new = new + "5"
            binding.result.setText(new)
        }
        binding.six.setOnClickListener {
            new = new + "6"
            binding.result.setText(new)
        }
        binding.seven.setOnClickListener {
            new = new + "7"
            binding.result.setText(new)
        }
        binding.eight.setOnClickListener {
            new = new + "8"
            binding.result.setText(new)
        }
        binding.nine.setOnClickListener {
            new = new + "9"
            binding.result.setText(new)
        }
        binding.zero.setOnClickListener {
            new = new + "0"
            binding.result.setText(new)
        }
        binding.plus.setOnClickListener {
            old = (old.toInt() + new.toInt()).toString()
            new = "0"
            binding.result.setText(old)
        }
        binding.clear.setOnClickListener {
            new = "0"
            old = "0"
            binding.result.setText("0")
        }
    }
}