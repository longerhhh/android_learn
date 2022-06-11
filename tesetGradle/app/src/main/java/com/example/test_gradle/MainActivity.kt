package com.example.test_gradle

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<TextView>(R.id.main_tv).apply {
            text = PRICE_TEXT + "_" + COUNTRY_TEXT + "_" + MAIN_TEXT
        }
        val a:String = "asdfa"
        a.substring(2)
    }
}