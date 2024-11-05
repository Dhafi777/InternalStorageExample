package com.dhafi.internalstorageexample

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.dhafi.internalstorageexample.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySecondBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBack.setOnClickListener {
            finish()

        }
    }
    override fun onResume() {
        super.onResume()
        loadData()
    }
    private fun loadData() {
        Thread {
            val input = openFileInput(MainActivity.FILE_NAME)
            input.use {
                var buffer = StringBuilder()
                var bytesRead = input.read()
                while (bytesRead != -1) {
                    buffer.append(bytesRead.toChar())
                    bytesRead = input.read()
                }

                runOnUiThread {

                    binding.tvOutputText.text = buffer.toString()
                }
            }
        }.start()
    }
}