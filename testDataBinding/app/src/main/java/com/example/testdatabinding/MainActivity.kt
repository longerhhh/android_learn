package com.example.testdatabinding

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.util.Log
import android.widget.Toast
import com.example.testdatabinding.databinding.ActivityMainBinding
import kotlin.concurrent.thread

class MainActivity : AppCompatActivity() {
    val TAG = this.javaClass.simpleName
    val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)
        binding.a= 1000.toString()
        binding.textView.setOnClickListener {
            Log.d(TAG, "onCreate: a = ${binding.a}")
            val i = (binding.a?.toInt() ?: 0) + 1
            Toast.makeText(this, "i = $i, a = ${binding.a}", Toast.LENGTH_SHORT).show()
            binding.a = i.toString()
        }
//        thread {
//            while (true) {
//                SystemClock.sleep(2000)
//                runOnUiThread {
//                    binding.switchChecked = binding.switchChecked != true
//                }
//            }
//        }
    }
}