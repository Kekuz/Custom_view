package com.example.custom_view_test

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.custom_view_test.databinding.ActivityMainBinding
import kotlin.math.abs
import kotlin.math.cos
import kotlin.math.sin
import kotlin.math.tan

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setButtons()

    }

    private fun setButtons() = with(binding) {
        button1.setOnClickListener {
            myCustomView.setFunction { x -> sin(x) }.invalidate()
        }
        button2.setOnClickListener {
            myCustomView.setFunction { x -> cos(x) }.invalidate()
        }
        button3.setOnClickListener {
            myCustomView.setFunction { x -> x * x }.invalidate()
        }
        button4.setOnClickListener {
            myCustomView.setFunction { x -> x * x * x }.invalidate()
        }
    }
}